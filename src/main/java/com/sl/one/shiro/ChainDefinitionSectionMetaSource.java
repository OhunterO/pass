/*
 * ====================================================================
 * 在线采购管理系统
 * ====================================================================
 */
package com.sl.one.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;


/**
 * 系统启动时,Shiro加载系统权限资源类
 *
 * @author NTT
 * @since 2016-11-15
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{
	
	private String filterChainDefinitions;
	
//	@Autowired
//	private PerPermissionMapper perPermissionMapper;
	
	private static Logger logger = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);
	/**
	 * 从数据库读取预先定义好的鉴权规则
	 */
	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini(); 		 
		//加载配置文件中默认的url配置
        ini.load(filterChainDefinitions); 
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME); 
        Map<String,Object> columnMap=new HashMap<String,Object>();
        columnMap.put("DEL_FLG", "0");
//        List<PerPermission> perPermissionList=perPermissionMapper.selectByMap(columnMap);
//        for (PerPermission perPermission : perPermissionList) {
//			if(perPermission.getPermissionUrl()!=null&&!"".equals(perPermission.getPermissionUrl())){
//				section.put(perPermission.getPermissionUrl(),"perms["+perPermission.getPermissionValue()+"]");
//			}
//		}
//        section.put("/**", "user");
//        logger.debug("******************加载系统权限资源成功******************");
		return section;
	}

	@Override
	public Class<?> getObjectType() {

		return this.getClass();
	}

	@Override
	public boolean isSingleton() {

		return false;
	}
	
	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

}
