package com.relaxed.common.core.flow.job;

import com.relaxed.common.core.flow.step.StepHandler;

/**
 * @author Yakir
 * @Topic AbstractJob
 * @Description
 * @date 2021/7/11 11:10
 * @Version 1.0
 */
public abstract class AbstractJob implements Job {
    private String name;

    /**
     * 步骤执行器
     */
    private StepHandler stepHandler;

    public AbstractJob(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
