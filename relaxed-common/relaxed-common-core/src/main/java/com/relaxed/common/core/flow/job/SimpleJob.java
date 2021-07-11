package com.relaxed.common.core.flow.job;

import com.relaxed.common.core.flow.step.Step;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yakir
 * @Topic SimpleJob
 * @Description
 * @date 2021/7/11 11:16
 * @Version 1.0
 */
public class SimpleJob extends AbstractJob{

    private List<Step> steps = new ArrayList<>();
    public SimpleJob() {
        this(null);
    }

    public SimpleJob(String name) {
        super(name);
    }

    /**
     * Public setter for the steps in this job. Overrides any calls to
     * {@link #addStep(Step)}.
     *
     * @param steps the steps to execute
     */
    public void setSteps(List<Step> steps) {
        this.steps.clear();
        this.steps.addAll(steps);
    }

    /**
     * Convenience method for adding a single step to the job.
     *
     * @param step a {@link Step} to add
     */
    public void addStep(Step step) {
        this.steps.add(step);
    }


}
