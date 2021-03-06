package com.relaxed.common.jsch.sftp.factory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.relaxed.common.jsch.sftp.exception.SftpClientException;
import com.relaxed.common.jsch.sftp.utils.ByteUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认实现
 *
 * @author shuoyu
 */
@Slf4j
public class DefaultSftp extends AbstractSftp {

	public DefaultSftp(ChannelSftp channelSftp) {
		super(channelSftp);
	}

	@Override
	public boolean isExist(String path) {
		try {
			getChannelSftp().lstat(path);
			return true;
		}
		catch (SftpException e) {
			log.error("option error", e);
			return false;
		}
	}

	@Override
	public InputStream getInputStream(String dir, String name) {
		if (!isExist(dir)) {
			throw new SftpClientException(String.format("the directory (%s) does not exist.", dir));
		}
		String absoluteFilePath = dir + "/" + name;
		if (!isExist(absoluteFilePath)) {
			throw new SftpClientException(String.format("the file (%s) does not exist.", absoluteFilePath));
		}
		try {
			channelSftp.cd(dir);
			return channelSftp.get(name);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp get remote file input stream error", e);
		}
	}

	@Override
	public File download(String dir, String name, File file) {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			if (!isExist(dir)) {
				throw new SftpClientException(String.format("the directory (%s) does not exist.", dir));
			}
			String absoluteFilePath = dir + "/" + name;
			if (!isExist(absoluteFilePath)) {
				throw new SftpClientException(String.format("the file (%s) does not exist.", absoluteFilePath));
			}
			channelSftp.get(absoluteFilePath, fos);
			return file;
		}
		catch (Exception e) {
			throw new SftpClientException("sftp get remote file download error", e);
		}
	}

	@Override
	public File download(String absoluteFilePath, File file) {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			if (!isExist(absoluteFilePath)) {
				throw new SftpClientException(String.format("the file (%s) does not exist.", absoluteFilePath));
			}
			channelSftp.get(absoluteFilePath, fos);
			return file;
		}
		catch (Exception e) {
			throw new SftpClientException("sftp get remote file download error", e);
		}
	}

	@Override
	public byte[] download(String dir, String name) {
		InputStream in = getInputStream(dir, name);
		return ByteUtil.inputStreamToByteArray(in);
	}

	@Override
	public void upload(String dir, String name, InputStream in) {
		try {
			mkdirs(dir);
			channelSftp.cd(dir);
			channelSftp.put(in, name);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp upload file error", e);
		}
	}

	@Override
	public void upload(String dir, String name, File file) {
		try (FileInputStream fis = new FileInputStream(file)) {
			upload(dir, name, fis);
		}
		catch (SftpClientException | IOException e) {
			throw new SftpClientException("sftp upload file error", e);
		}
	}

	@Override
	public void upload(String dir, String name, String src) {
		try {
			mkdirs(dir);
			channelSftp.cd(dir);
			channelSftp.put(src, name);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp upload file error", e);
		}
	}

	@Override
	public void delete(String dir) {
		if (!isDir(dir)) {
			return;
		}
		if (!isExist(dir)) {
			return;
		}
		try {
			channelSftp.rmdir(dir);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp delete directory error", e);
		}
	}

	@Override
	public void delete(String dir, String name) {
		if (!isDir(dir)) {
			return;
		}
		String absoluteFilePath = dir + "/" + name;
		if (!isExist(absoluteFilePath)) {
			return;
		}
		try {
			channelSftp.cd(dir);
			channelSftp.rm(name);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp delete file error", e);
		}
	}

	@Override
	public void mkdirs(String dir) {
		String[] folders = dir.split("/");
		try {
			channelSftp.cd("/");
			for (String folder : folders) {
				if (folder.length() > 0) {
					try {
						channelSftp.cd(folder);
					}
					catch (Exception e) {
						channelSftp.mkdir(folder);
						channelSftp.cd(folder);
					}
				}
			}
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp create directory error", e);
		}
	}

	@Override
	public boolean isDir(String path) {
		try {
			SftpATTRS attrs = channelSftp.lstat(path);
			return attrs.isDir();
		}
		catch (SftpException e) {
			return false;
		}
	}

	@Override
	public List<String> list(String path) {
		List<String> result = new ArrayList<>();
		ChannelSftp.LsEntrySelector selector = lsEntry -> {
			String filename = lsEntry.getFilename();
			if (!".".equals(filename) && !"..".equals(filename)) {
				result.add(filename);
			}
			return ChannelSftp.LsEntrySelector.CONTINUE;
		};
		try {
			channelSftp.ls(path, selector);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp view directory error", e);
		}
		return result;
	}

	@Override
	public void move(String src, String target) {
		try {
			channelSftp.rename(src, target);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp move file error", e);
		}
	}

	@Override
	public void move(String src, String target, String fileName) {
		try {
			mkdirs(target);
			channelSftp.cd(target);
			channelSftp.rename(src, fileName);
		}
		catch (SftpException e) {
			throw new SftpClientException("sftp move file error", e);
		}
	}

	@Override
	public void chmod(String permissions, String path) {
		if (permissions == null) {
			throw new SftpClientException("permissions can not be empty.");
		}
		if (permissions.length() != 3) {
			throw new SftpClientException("the permission must be 3 digits 0-7");
		}
		for (char c : permissions.toCharArray()) {
			int i;
			try {
				i = Integer.parseInt(String.valueOf(c));
			}
			catch (NumberFormatException e) {
				throw new SftpClientException("the permission must be 3 digits 0-7");
			}
			if (i > 7 || i < 0) {
				throw new SftpClientException("the permission must be 3 digits 0-7");
			}
			Integer p = Integer.valueOf(permissions, 8);
			try {
				channelSftp.chmod(p, path);
			}
			catch (SftpException e) {
				throw new SftpClientException("sftp modify permissions error", e);
			}
		}

	}

}
