package com.relaxed.common.core.flow;

import com.relaxed.common.core.flow.job.JobBuilder;
import com.relaxed.common.core.flow.job.SimpleJob;
import com.relaxed.common.core.flow.job.SimpleJobBuilder;

/**
 * @author Yakir
 * @Topic JobBuilderFactory
 * @Description  逻辑支持-  1.通过工作工厂构建任务
 *                         2. jobExecution 定位当前执行步骤所处位置
 *                         3.stepHandler 处理实际步骤 返回StepExecution执行结果
 *                         4. 根据步骤执行结果 更改jobExecution 执行状态
 *                         5. 判断有无任务有 则继续向下执行 无则结果
 * @date 2021/7/11 11:08
 * @Version 1.0
 */
public class JobBuilderFactory {

    public JobBuilder get(String name){
        return new JobBuilder(name);
    }
}
