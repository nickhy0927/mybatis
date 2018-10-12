package com.mybatis.core.orm.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射的 Utils 函数集合
 * 提供访问私有变量, 获取泛型类型 Class, 提取集合中元素属性等 Utils 函数
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class ReflectionUtils {

	/**
     * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     * @param clazz
     * @param index
     * @return
     */
	public static Class getSuperClassGenricType(Class clazz, int index){
        Type genType = clazz.getGenericSuperclass();
        if(!(genType instanceof ParameterizedType)){
            return Object.class;
        }
        Type [] params = ((ParameterizedType)genType).getActualTypeArguments();
        
        if(index >= params.length || index < 0){
            return Object.class;
        }
        if(!(params[index] instanceof Class)){
            return Object.class;
        }
        return (Class) params[index];
    }
    
    /**
     * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
     * 如: public EmployeeDao extends BaseDao<Employee, String>
     * @param <T>
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static<T> Class<T> getSuperGenericType(Class clazz){
        return getSuperClassGenricType(clazz, 0);
    }
    
    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     * @param object
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes){
    	Class<?> superClass = object.getClass();
        try {
            return superClass.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected)
     * @param object
     * @param methodName
     * @param parameterTypes
     * @param parameters
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes,
            Object [] parameters) throws InvocationTargetException{
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if(method == null){
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, parameters);
        } catch(IllegalAccessException e) {
            System.out.println("不可能抛出的异常");
        } 
        return null;
    }
    
}
