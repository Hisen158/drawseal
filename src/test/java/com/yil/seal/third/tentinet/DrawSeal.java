package com.yil.seal.third.tentinet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Properties;

public class DrawSeal {
	/**
	 * ����ӡ�²��ϴ���������
	 * @param seal
	 * @param templateId
	 * @param elementTypeId
	 * @param colorTypeId
	 * @return  ������ӡ������
	 */
	private String drawSeal(String userNo,String templateId, int elementTypeId, int colorTypeId){
		
		//������ɫID�����ɫ��ֵ
		SealTemplateColorType colorType=colorTypeMapper.selectByPrimaryKey(colorTypeId);
		String color=colorType.getSealTemplateColorTypeName();

		//����ģ��ID����ɫID�鵽ģ����ɫ ����
		SealTemplateColor templateColorTem=new SealTemplateColor();
		templateColorTem.setSealTemplateColorRelId(templateId);
		templateColorTem.setSealTemplateColorTypeId(colorTypeId);
		SealTemplateColor templateColor=colorMapper.selectByTemplateAndColor(templateColorTem);
		
		//��ȡʾ��ͼURL
		String url=templateColor.getSealTemplateColorUrl();
		
		//����ģ��ID��Ԫ������ID�Լ���ɫ�������Ҫ��ӵ�ӡ��Ԫ��
		SealElement   elementTem=new SealElement();
		elementTem.setSealElementTemplateId(templateId);
		elementTem.setSealElementTypeId(1);
		elementTem.setSealElementFontColor(color);
		SealElement   element1=elementMapper.selectByTemplateIdAndTypeIdAndColor(elementTem);    //Ԫ��1���û�ID
		
		elementTem.setSealElementTypeId(2);
		SealElement   element2=elementMapper.selectByTemplateIdAndTypeIdAndColor(elementTem);    //Ԫ��2���û��ǳ�
		
		//�������ݲ������ж��û�ϣ����ӵ����ݣ�1Ϊ�˺ţ�2Ϊ�ǳƣ�
	    Map<String, Object> accountMap=accountMapper.findUser(userNo);
	    String[] content=new String[2];
			content[0]="#"+userNo;
			content[1]=(String) accountMap.get("c_user_nick");
		
		//����ģ��ͼƬ
		EditPics edit = new EditPics();
		Properties props = new Properties();  
		BufferedImage image = edit.loadImageUrl(ConfigUtil.get("downloadUrl")+url);
		
		logger.infobk("ӡ��ͼƬ·������<==", ConfigUtil.get("downloadUrl")+url);
		
		//��ģ��ͼƬ�����Ԫ��
			
		   // Ԫ������
			String[] location=element1.getSealElementLocation().split(",");
			int x=Integer.valueOf(location[0]);
			int y=Integer.valueOf(location[1]);
			
			String[] location2=element2.getSealElementLocation().split(",");
			int m=Integer.valueOf(location2[0]);
			int n=Integer.valueOf(location2[1]);
								
			//����ģ�塢���ꡢ���ݡ�Ԫ�أ�����ӡ��ͼƬ
			image=edit.modifyImage(image,content,x,y,m,n,false,element1,element2) ;
			
				
			//���ڱ���
			edit.writeImageLocal(Class.class.getClass().getResource("/").getPath()+"yinzhang.png",image);
			
			//�ϴ�������
			Map<String, Object> returnMap=uploadSeal(Class.class.getClass().getResource("/").getPath()+"yinzhang.png");
			//ɾ�������ļ�
			File file=new File(Class.class.getClass().getResource("/").getPath()+"yinzhang.png");
			if(file.exists()){
				file.delete();
			}
			//��ȡӡ�´洢URL
			String sealUrl=returnMap.get("name").toString();
			
			return sealUrl;
		
	}
	
}
