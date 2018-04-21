package com.lac.ws;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.ws.WebServiceContext;

import com.lac.dao.UserDao;
import com.lac.exception.UserRunTimeException;
import com.lac.po.User;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;

@WebService(endpointInterface = "com.lac.ws.IUserService", serviceName = "UserService", portName = "userServicePort", targetNamespace = "http://ws.lac.com")
public class UserServiceImpl implements IUserService {

	private UserDao userDao = UserDao.newInstence();

	@Resource
	private WebServiceContext ctx;

	@Override
	public void add(User user) throws UserRunTimeException {
		checkRegister();  
		userDao.add(user);
	}

	@Override
	public void delete(String username) throws UserRunTimeException {
		checkRegister();  
		userDao.delete(username);
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

	@Override
	public User login(String username, String password)
			throws UserRunTimeException {
		return userDao.login(username, password);
	}

	private void checkRegister() throws UserRunTimeException{
		
		/*Map<String, Object> map = ctx.getMessageContext();
		for(Entry<String, Object> entry:map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue()); 
		}*/
		HeaderList headers = (HeaderList) ctx.getMessageContext().get(
				JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
		QName qname = new QName("soaReqHeader");
		if (headers == null){
			throw new UserRunTimeException("没有消息头，该功能需要进行权限控制");
		}
		Header header = headers.get(qname, true);
		if (header == null){
			throw new UserRunTimeException("消息内容为空，该功能需要进行权限控制");
		}
		JAXBContext jc = JAXBContext.newInstance(User.class);
	     Unmarshaller u = jc.createUnmarshaller();
		header.readAsJAXB(u);
	}

	private User x2User(XMLStreamReader xsr1) throws XMLStreamException {
		StreamReaderDelegate xsr = (StreamReaderDelegate) xsr1;
		System.out.println(xsr.getText());
		User u = new User();
		while (xsr.hasNext()) {
			int type = xsr.next();
			if (type == XMLEvent.START_ELEMENT) {
				String name = xsr.getName().toString();
				System.out.println(name);
				if (name.equals("username")) {
					u.setUsername(xsr.getElementText());
				} else if (name.equals("password")) {
					u.setPassword(xsr.getElementText());
				} else if (name.equals("nickname")) {
					u.setNickname(xsr.getElementText());
				}
			}
		}
		System.out.println(u.getUsername());
		return u;
	}

}
