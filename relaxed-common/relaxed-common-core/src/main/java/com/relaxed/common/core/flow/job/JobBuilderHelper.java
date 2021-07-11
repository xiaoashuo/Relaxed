package com.relaxed.common.core.flow.job;

/**
 * @author Yakir
 * @Topic JobBuilderHepler
 * @Description
 * @date 2021/7/11 11:33
 * @Version 1.0
 */
public class JobBuilderHelper <B extends JobBuilderHelper<B>>{
    private String name;

    public JobBuilderHelper(String name) {
        this.name = name;
    }


    protected String getName() {
        return name;
    }
}
