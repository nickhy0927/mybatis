<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packages}.${domainObjectName?lower_case}.dao.${domainObjectName}Mapper">
	 <#assign domainName="${domainObjectName?uncap_first}"/> 
	<insert id="insert" parameterType="${packages}.${domainName?lower_case}.entity.${domainObjectName}">
		insert into ${tableName}(
		<#list columnList as column>
			<#if column_has_next>
				${column.actualColumnName?uncap_first},
			<#else> 
				${column.actualColumnName?uncap_first}
			</#if>
		</#list>
		)
		values (
		<#list columnList as column>
			<#if column_has_next>
				${r'#{'}${column.javaProperty?uncap_first}${'}'},
			<#else> 
				${r'#{'}${column.javaProperty?uncap_first}${'}'}
			</#if>
		</#list>
		)
	</insert>

	<delete id="delete" parameterType="java.lang.String">
		delete from ${tableName} where id = ${r"#{id}"}
	</delete>

	<delete id="deleteBatch" parameterType="java.util.List">
		delete from ${tableName} where id in 
		<foreach item="id" collection="list" open="(" separator="," close=")">
			${r'#{id}'}
		</foreach>
	</delete>

	<update id="update" parameterType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		update ${tableName} 
		<trim prefix="set" suffixOverrides=",">
			<#list columnList as column>
			<if test="${column.javaProperty?uncap_first} != null">${column.actualColumnName} = ${"#{" + column.javaProperty?uncap_first + "}"},</if>
			</#list>
		</trim>	
		where id = ${r'#{id}'}
	</update>
	
	<update id="updateBatch" parameterType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		update ${tableName} 
		<trim prefix="set" suffixOverrides=",">
			<#list columnList as column>
			<if test="${column.javaProperty?uncap_first} != null">${column.actualColumnName} = ${"#{" + column.javaProperty?uncap_first + "}"},</if>
			</#list>
		</trim>	
		where id = ${r'#{id}'}
	</update>

	<select id="get" parameterType="String" resultType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		select 
		<#list columnList as column>
			<#if column_has_next>${column.actualColumnName},
			<#else>${column.actualColumnName}
			</#if>
		</#list>
		from ${tableName} where id = ${r'#{id}'}
	</select>
	<select id="getObject" 
			parameterType=${packages}.${domainObjectName?lower_case}.entity.${domainObjectName} 
			resultType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		select 
		<#list columnList as column>
			<#if column_has_next>${column.actualColumnName},
			<#else>${column.actualColumnName}
			</#if>
		</#list>
		from ${tableName} where id = ${r'#{id}'}
	</select>

	<select id="queryListByMap" parameterType="java.util.Map" resultType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		select 
		<#list columnList as column>
			<#if column_has_next>${column.actualColumnName},
			<#else>${column.actualColumnName}
			</#if>
		</#list>
		from ${tableName}
		<include refid="conditions" />
	</select>
	
	<select id="queryListByObject" 
			parameterType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}" 
			resultType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		select 
		<#list columnList as column>
			<#if column_has_next>${column.actualColumnName},
			<#else>${column.actualColumnName}
			</#if>
		</#list>
		from ${tableName}
		<include refid="conditions" />
	</select>

	<select id="queryPageByMap" parameterType="java.util.Map" resultType="${packages}.${domainObjectName?lower_case}.entity.${domainObjectName}">
		select 
		<#list columnList as column>
			<#if column_has_next>${column.actualColumnName},
			<#else>${column.actualColumnName}
			</#if>
		</#list>
		from ${tableName}
		<include refid="conditions" />
		order by
            <choose>
                <when test="order != null">${r'${order}'} ${r'${sort}'}</when>
                <when test="order != null">create_time desc</when>
            </choose>
	</select>

	<sql id="conditions">
		<trim prefix="where" prefixOverrides="and|or">
			<#list columnList as column>
			<if test="${column.javaProperty?uncap_first} != null and ${column.javaProperty?uncap_first} != ''">and ${column.actualColumnName?uncap_first} = ${"#{" + column.javaProperty?uncap_first + "}"}</if>
			</#list>
		</trim>
	</sql>
</mapper>
