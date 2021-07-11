package com.relaxed.common.core.flow.job;

import com.relaxed.common.core.flow.step.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yakir
 * @Topic SimpleJobBuilder
 * @Description
 * @date 2021/7/11 11:31
 * @Version 1.0
 */
public class SimpleJobBuilder extends JobBuilderHelper<SimpleJobBuilder>{
    private List<Step> steps = new ArrayList<>();

    public SimpleJobBuilder(String name) {
        super(name);
    }

    /**
     * 构建job
     * @return
     */
    public Job build() {
        SimpleJob job = new SimpleJob(getName());
        job.setSteps(steps);
        return job;
    }
    /**
     * Start the job with this step.
     *
     * @param step a step to start with
     * @return this for fluent chaining
     */
    public SimpleJobBuilder start(Step step) {
        if (steps.isEmpty()) {
            steps.add(step);
        }
        else {
            steps.set(0, step);
        }
        return this;
    }

    /**
     * Continue or end a job with this step if the previous step was successful.
     *
     * @param step a step to execute next
     * @return this for fluent chaining
     */
    public SimpleJobBuilder next(Step step) {
        steps.add(step);
        return this;
    }

}
