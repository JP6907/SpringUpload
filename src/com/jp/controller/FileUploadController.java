package com.jp.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpHandler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jp.bean.User;

@Controller
public class FileUploadController {
	private static final Log logger = LogFactory.getLog(FileUploadController.class);
	@RequestMapping(value="/{formName}")
	public String loginForm(@PathVariable String formName){
		logger.info(formName);
		return formName; //��ת��jspҳ��
	}
	//�ļ��ϴ�
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@RequestParam("description")String description,
			@RequestParam("file")MultipartFile file) throws Exception{
		System.out.println("�ļ�����:" + description);
		if(!file.isEmpty()){
			//�ϴ��ļ�·��
			String path = request.getServletContext().getRealPath("/images/");
			//�ϴ��ļ���
			String filename = file.getOriginalFilename();
			System.out.println("�ļ�����·��:" + path);
			System.out.println("�ļ���:" + filename); 
			File filepath = new File(path,filename);
			if(!filepath.getParentFile().exists()){
				filepath.getParentFile().mkdirs();
			}
			file.transferTo(new File(path+File.separator + filename));
			return "success";
		}else{
			return "error";
		}
	}
	//ʹ�ö�������ϴ�
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String upload(HttpServletRequest request,
			@ModelAttribute User user,
			Model model) throws Exception{
		System.out.println(user.getUsername());
		if(!user.getImage().isEmpty()){
			//�ϴ��ļ�·��
			String path = request.getServletContext().getRealPath("/images/"+user.getUsername()+"/");
			//�ϴ��ļ���
			String filename = user.getImage().getOriginalFilename();
			System.out.println("�ļ�����·��:" + path);
			System.out.println("�ļ���:" + filename); 
			File filepath = new File(path,filename);
			if(!filepath.getParentFile().exists()){
				filepath.getParentFile().mkdirs();
			}
			user.getImage().transferTo(new File(path+File.separator + filename));
			model.addAttribute("user", user);//�û���ӵ�model��
			return "userinfo";
		}else{
			return "error";
		}	
	}
	//�ļ�����
	@RequestMapping(value="download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			@RequestParam("username")String username,
			@RequestParam("filename")String filename,
			Model model) throws Exception{
		String path = request.getServletContext().getRealPath("/images/"+username+"/");
		System.out.println(path);
		File file = new File(path+File.separator + filename);
		HttpHeaders headers = new HttpHeaders();
		//������ʾ�����������������������
		String downloadname = new String(filename.getBytes("UTF-8"),"iso-8859-1");
		//֪ͨ�������attachment���ط�ʽ��ͼƬ
		headers.setContentDispositionFormData("attachment", downloadname);
		//application/cotet-stream:�����������ݣ�������ļ����ط�ʽ
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//201 HttpStatus.CREATED
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
}
