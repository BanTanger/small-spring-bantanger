package com.bantanger.springframework.aop;

/**
 * @author BanTanger 半糖
 * @Date 2023/2/13 22:23
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 返回此 {@link TargetSource} 返回的目标类型。
     * 可以返回 null，尽管 TargetSource 的某些用法可能只适用于预定的目标类。
     * @return
     */
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    /**
     * 返回目标实例。在 AOP 框架调用 AOP 方法调用的“目标”之前立即调用。
     * @return
     */
    public Object getTarget() {
        return this.target;
    }
}