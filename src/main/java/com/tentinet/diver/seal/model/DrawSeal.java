package com.tentinet.diver.seal.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Properties;

public class DrawSeal {
	/**
	 * 画出印章并上传至服务器
	 * @param seal
	 * @param templateId
	 * @param elementTypeId
	 * @param colorTypeId
	 * @return  画出的印章链接
	 */
	private String drawSeal(String userNo,String templateId, int elementTypeId, int colorTypeId){
		
		//根据颜色ID查出颜色的值
		SealTemplateColorType colorType=colorTypeMapper.selectByPrimaryKey(colorTypeId);
		String color=colorType.getSealTemplateColorTypeName();

		//根据模板ID、颜色ID查到模板颜色 对象
		SealTemplateColor templateColorTem=new SealTemplateColor();
		templateColorTem.setSealTemplateColorRelId(templateId);
		templateColorTem.setSealTemplateColorTypeId(colorTypeId);
		SealTemplateColor templateColor=colorMapper.selectByTemplateAndColor(templateColorTem);
		
		//获取示例图URL
		String url=templateColor.getSealTemplateColorUrl();
		
		//根据模板ID和元素类型ID以及颜色，查出需要添加的印章元素
		SealElement   elementTem=new SealElement();
		elementTem.setSealElementTemplateId(templateId);
		elementTem.setSealElementTypeId(1);
		elementTem.setSealElementFontColor(color);
		SealElement   element1=elementMapper.selectByTemplateIdAndTypeIdAndColor(elementTem);    //元素1，用户ID
		
		elementTem.setSealElementTypeId(2);
		SealElement   element2=elementMapper.selectByTemplateIdAndTypeIdAndColor(elementTem);    //元素2，用户昵称
		
		//根据内容参数，判断用户希望添加的内容（1为账号，2为昵称）
	    Map<String, Object> accountMap=accountMapper.findUser(userNo);
	    String[] content=new String[2];
			content[0]="#"+userNo;
			content[1]=(String) accountMap.get("c_user_nick");
		
		//导入模板图片
		EditPics edit = new EditPics();
		Properties props = new Properties();  
		BufferedImage image = edit.loadImageUrl(ConfigUtil.get("downloadUrl")+url);
		
		logger.infobk("印章图片路径参数<==", ConfigUtil.get("downloadUrl")+url);
		
		//在模板图片上添加元素
			
		   // 元素坐标
			String[] location=element1.getSealElementLocation().split(",");
			int x=Integer.valueOf(location[0]);
			int y=Integer.valueOf(location[1]);
			
			String[] location2=element2.getSealElementLocation().split(",");
			int m=Integer.valueOf(location2[0]);
			int n=Integer.valueOf(location2[1]);
								
			//根据模板、坐标、内容、元素，生成印章图片
			image=edit.modifyImage(image,content,x,y,m,n,false,element1,element2) ;
			
				
			//存在本地
			edit.writeImageLocal(Class.class.getClass().getResource("/").getPath()+"yinzhang.png",image);
			
			//上传服务器
			Map<String, Object> returnMap=uploadSeal(Class.class.getClass().getResource("/").getPath()+"yinzhang.png");
			//删除本地文件
			File file=new File(Class.class.getClass().getResource("/").getPath()+"yinzhang.png");
			if(file.exists()){
				file.delete();
			}
			//获取印章存储URL
			String sealUrl=returnMap.get("name").toString();
			
			return sealUrl;
		
	}
	
}
