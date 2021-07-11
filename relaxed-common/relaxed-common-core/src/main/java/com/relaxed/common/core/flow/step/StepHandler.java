package com.relaxed.common.core.flow.step;

import com.relaxed.common.core.flow.execution.JobExecution;
import com.relaxed.common.core.flow.execution.StepExecution;

/**
 * @author Yakir
 * @Topic StepHandle
 * @Description
 * @date 2021/7/11 12:05
 * @Version 1.0
 */
public interface StepHandler {
    /**
     * 步骤执行器
     * @param step
     * @param jobExecution
     * @return
     */
    StepExecution handleStep(Step step, JobExecution jobExecution);
}
