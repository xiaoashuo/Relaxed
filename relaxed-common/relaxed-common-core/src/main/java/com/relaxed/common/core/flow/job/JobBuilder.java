package com.relaxed.common.core.flow.job;

import com.relaxed.common.core.flow.step.Step;

/**
 * @author Yakir
 * @Topic JobBuiler
 * @Description
 * @date 2021/7/11 11:31
 * @Version 1.0
 */
public class JobBuilder extends JobBuilderHelper<JobBuilder>{
    public JobBuilder(String name) {
       super(name);
    }

    public SimpleJobBuilder start(Step step){
        return new SimpleJobBuilder(getName());
    }
}
