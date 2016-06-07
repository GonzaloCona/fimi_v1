package RyC.fimiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import javax.ws.rs.MatrixParam;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import RyC.dbHIB.*;
import RyC.fimiModel.*;


import com.google.android.gcm.server.Message;
//import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Properties;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class FimiService {
	
	
	public List<ObtenerGrafico> getDatos(int id){
		List<ObtenerGrafico> lista = new ArrayList<ObtenerGrafico>();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		try{
			
			DateHandler fechaActual = new DateHandler();
			
			//DateHandler fechaActual2 = new DateHandler();
			int count;
			int dias=-6;
	        //fechaActual2=sumarRestarDiasFecha(fechaActual,-1);
			
	    	query = session.createQuery("Select id_usuario_red from rs_usuario where n_usuario_red<>'sin_info' and id_usuario="+id);
			List<rs_usuario> Lrs = (List<rs_usuario>) query.list();
			System.out.println(Lrs);
			if(Lrs.size()>0){
				for(int i=0;i<7;i++){
					count=0;
					//System.out.println("ENTRA AL FOR");
					ObtenerGrafico og=new ObtenerGrafico();
					//System.out.println("LLAMA Y CUENTA!");
					//System.out.println("esta es la fecha: "+sumarRestarDiasFecha(fechaActual, dias).getDate());
					
					count=count+buscaEnHistorial(Lrs,sumarRestarDiasFecha(fechaActual, dias));
					//System.out.println("esta es la cantidad: "+count);
					og.setDato(count);
					//System.out.println("OBJETO a la lista: "+og);
					lista.add(og);
					dias++;
				}
			}else {
				    count=0;
				    System.out.println("getDatos : No tiene redes");}
						
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
			System.out.println("getDatos : Excepcion controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		return lista;
	}
	
	public int buscaEnHistorial(List<rs_usuario> lrs, DateHandler fecha){
		int count;
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		count=0;
		try{
			
			int l=lrs.size();
			
			for(int i=0;i < l ;i++){
				System.out.println("ENTRA al For en busca historial");
				java.sql.Date sqlDate = new java.sql.Date(fecha.getDate().getTime());
				System.out.println("id="+lrs.get(i)+" fecha:"+sqlDate);
				//System.out.println("from historial_usuario where id_usuario_red="+lrs.get(i)+" and fecha="+sqlDate);
				query = session.createQuery("from historial_usuario where id_usuario_red="+lrs.get(i)+" and fecha='"+sqlDate+"'");
				List<historial_usuario> Lhu = (List<historial_usuario>) query.list();
				//System.out.println("cantidad resultados en h_u:"+Lhu.size());
				count = count + Lhu.size();
				
			}
			
			//System.out.println("cantidad "+count);
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			System.out.println("buscaEnHistorial: Excepcion controlada");
		}
		session.getTransaction().commit();	
		session.close();
		return count;
	}
	
	
	
	 public DateHandler sumarRestarDiasFecha( DateHandler fecha, int dias){
		 		
		   try{    
		 	Calendar calendar = Calendar.getInstance();			
		       calendar.setTime(fecha.NewDate()); // Configuramos la fecha que se recibe
		       //System.out.println(calendar);
		       calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
		       //System.out.println(calendar.getTime());
		       DateHandler d =new DateHandler();
		       d.setDate(calendar.getTime());
		       return  d; // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos
		   }catch(Exception e){
			   System.out.println(e.getMessage());
			   System.out.println("sumarRestarDiasFecha: Excepcion controlada");
		   }
		   return null;
		 
	 }
	
	public String CalPush(String id, String cod,String con){
		
		Sender sender = new Sender("AIzaSyAe_zNh_S3HkeeTV37Cd1NCoR8tTYqYT34");
		
		RespuestaPush rp=new RespuestaPush();
		rp.setTipo(cod);
		rp.setCuerpo(con);		
		if(cod.equals("1")){
			rp.setDescripcion("Nuevo Mensaje");
    	}else{rp.setDescripcion("Publicidad");}
		
		Message message = new Message.Builder()
			.addData("title",rp.getTipo())
			.addData("message", rp.getDescripcion())
			.addData("body",rp.getCuerpo())
		    .build();
		
		//String id ="APA91bH2ZceuKL4QbZX06OpsI9RltBlaShO6mF4pjFUsxMJc63FCacAlEG6myw1MvcA7UlM9gnB9todjzMrmLY8OMtxi8znCNBYTyoQRfF-izl7bb4jXlWQ";
		try {
			System.out.println("*************************************");
			System.out.println("** CalPush: ");
			System.out.println("** id: "+id);
			System.out.println("** cod: "+cod);
			System.out.println("** con: "+con);
			System.out.println("** message: "+message.toString());
			System.out.println("** sender: "+sender.toString());
			System.out.println("");
			Result result = sender.send(message, id, 1);
			System.out.println("** Message Result: "+result.toString());
			System.out.println("*************************************");
			JSONObject jo =new JSONObject(message);
			return jo.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CalPush: No se esta enviando un puch"+id+" ; "+cod+" ; "+con);
			
		}
		return null;
	}
	
	public salidaStndr getContrasena(String correo ){
		salidaStndr salida = new salidaStndr();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		try{
			query = session.createQuery("from usuario where correo='"+correo+"'");
			List<usuario> users = (List<usuario>) query.list();
			
			if(users.size()>0){
				//correo
				Properties properties = new Properties();
				//String password = "4fimi2016";
				javax.mail.Session session2;
			
				properties.put("mail.smtp.host", "smtp.googlemail.com");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.port",587);
				properties.put("mail.smtp.mail.sender","no-reply@4fimi.com");
				properties.put("mail.smtp.user", "no-reply@4fimi.com");
				properties.put("mail.smtp.auth", "true");
				
				 session2 = javax.mail.Session.getDefaultInstance(properties);
				 session2.setDebug(true);
				try{
					MimeMessage message = new MimeMessage(session2);
					message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
					message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(correo));
					message.setSubject("Recuperacion clave 4fimi");
					message.setText("Estimad@ cliente, hemos recibido su solicitud para la recuperacion de su clave:"+users.get(0).getContraseña());
					Transport t = session2.getTransport("smtp");
					t.connect((String)properties.get("mail.smtp.user"), "4fimi2016");
					t.sendMessage(message, message.getAllRecipients());
					t.close();
				     salida.setCod_salida(0);
	                 salida.setDes_salida("sin fallo");
				}catch (MessagingException me){
		                        salida.setCod_salida(1);
		                        salida.setDes_salida(me.getMessage());
				}
				//
			}else {
				salida.setCod_salida(1);
				salida.setDes_salida("no existe");
			}
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("getContrasena: Excepcion Controlada.");
			
		}
		
		session.getTransaction().commit();	
		session.close();
		return salida;
	}
	

	public salidaStndr returnLoginWeb(String correo, String pass){
		salidaStndr salida=new salidaStndr();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		try{
			
			query = session.createQuery("from admin_user where correo='"+correo+"' and contrasena='"+pass+"'");
			List<admin_user> users = (List<admin_user>) query.list();
			if(users.size()>0){
				salida.setCod_salida(0);
				salida.setDes_salida("sin fallos");	
			}else{
				salida.setCod_salida(1);
				salida.setDes_salida("no existe");
			}
			
					
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("returnLoginWeb: Excepcion Controlada.");
		}
		
		session.getTransaction().commit();	
		session.close();
		return salida;
	}
	
	public ReturnLogin returnLogin(String correo, String pass){
		ReturnLogin rl=new ReturnLogin();
		//usuario ud = new usuario();
		
		rl.setCod_salida(0);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		try{
			query = session.createQuery("from usuario where correo='"+correo+"' and contrasena='"+pass+"'");
			List<usuario> users = (List<usuario>) query.list();
			
			if(users.size()>0){
			
			
				rl.setCod_salida(0);
				rl.setDes_salida("sin fallos");
				rl.setEstado(users.get(0).getEstado());
				rl.setIdUsuario(users.get(0).getId_usuario());
				rl.setNombre(users.get(0).getNom1()+" "+users.get(0).getApell1());
				rl.setPerfil(users.get(0).getPerfil_usuario());
			}else{
				rl.setCod_salida(1);
				rl.setDes_salida("No existe el usuario");
				rl.setEstado(0);
				rl.setIdUsuario(0);
				rl.setNombre(" ");
				rl.setPerfil(0);
			}
			
		}catch (Exception ex){
			rl.setCod_salida(1);
			rl.setDes_salida(ex.getMessage());
			rl.setEstado(0);
			rl.setIdUsuario(0);
			rl.setNombre(" ");
			rl.setPerfil(0);
			//throw new RuntimeException("Fallo al llamar 'createQuery'",ex);
			//throw rl;
			System.out.println("returnLogin: Excepcion Controlada.");
		}
		
		session.getTransaction().commit();
		session.close();
		return rl;
	}
	
	public ReturnLogin getLogin(String correo){
		ReturnLogin rl=new ReturnLogin();
		//usuario ud = new usuario();
		rl.setCod_salida(0);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		try{
			query = session.createQuery("from usuario where correo='"+correo+"'");
			List<usuario> users = (List<usuario>) query.list();
			
			if(users.size()>0){
			
			
				rl.setCod_salida(0);
				rl.setDes_salida("sin fallos");
				rl.setEstado(users.get(0).getEstado());
				rl.setIdUsuario(users.get(0).getId_usuario());
				rl.setNombre(users.get(0).getNom1()+" "+users.get(0).getApell1());
				rl.setPerfil(users.get(0).getPerfil_usuario());
			}else{
				rl.setCod_salida(1);
				rl.setDes_salida("No existe el usuario");
				rl.setEstado(0);
				rl.setIdUsuario(0);
				rl.setNombre(" ");
				rl.setPerfil(0);
			}
			
		}catch (Exception ex){
			rl.setCod_salida(1);
			rl.setDes_salida(ex.getMessage());
			rl.setEstado(0);
			rl.setIdUsuario(0);
			rl.setNombre(" ");
			rl.setPerfil(0);
			//throw new RuntimeException("Fallo al llamar 'createQuery'",ex);
			//throw rl;
			System.out.println("getLogin: Excepcion Controlada.");
		}
		
		session.getTransaction().commit();
		session.close();
		return rl;
		
	}
	
	/////////////////////Mantenedores/////////////////////////
	//REDES
	public List<ObtenerRedxUser> getredes(int id){
		List<ObtenerRedxUser> oru=new ArrayList<ObtenerRedxUser>();
		//usuario ud = new usuario();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		try{
			query = session.createQuery("from rs_usuario where id_usuario="+id);
			List<rs_usuario> users = (List<rs_usuario>) query.list();
			
			for(rs_usuario user: users){
				ObtenerRedxUser u=new ObtenerRedxUser();
				
				u.setCodSalida(0);
				u.setDes_salida("sin fallo");			
				u.setId_red_social(user.getId_rs_usuario().getId_red_social());
				u.setId_usuario(user.getId_usuario());
				u.setId_usuario_red(user.getId_usuario_red());
				u.setN_usuario_red(user.getId_rs_usuario().getN_usuario_red());		
				oru.add(u);
			}
		}catch (Exception ex){
			ObtenerRedxUser x=new ObtenerRedxUser();
			x.setCodSalida(1);
			x.setDes_salida(ex.getMessage());			
			x.setId_red_social(0);
			x.setId_usuario(0);
			x.setId_usuario_red(0);
			x.setN_usuario_red(" ");
			//oru=null;
			oru.add(x);
			//throw new RuntimeException("Fallo al llamar 'createQuery'",ex);
			System.out.println("getredes: Excepcion Controlada.");
		}
		
		session.getTransaction().commit();
		session.close();
		return oru;
	}
	
	public salidaStndr insertRedes(String usuario,int id_usuario,int id_red){
		salidaStndr salida = new salidaStndr();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		session.beginTransaction();
		session2.beginTransaction();
		Query query;
		int idInsta=0;
		
		try{
			
			query = session.createQuery("from rs_usuario where id_red_social="+id_red+" and id_usuario="+id_usuario+" and n_usuario_red='"+ usuario+"'");
			List<rs_usuario> p = (List<rs_usuario>) query.list();
			//System.out.println("!!!!"+p.size());
			if(p.size()==0){
				key_rs_usuarios key = new key_rs_usuarios();
				rs_usuario newred = new rs_usuario();
				
				if(id_red==2){
					
					/*key.setId_red_social(2);
					key.setN_usuario_red("sin_info");
					Object u = session.load(rs_usuario.class, key);
					rs_usuario rs_usuarioo=(rs_usuario)u;*/
					query = session.createQuery("from rs_usuario where id_red_social=2 and id_usuario="+id_usuario+" and n_usuario_red='sin_info'");
					
					List<rs_usuario> lrs = (List<rs_usuario>) query.list();
					if(lrs.size()==0){			
						key.setId_red_social(2);
						key.setN_usuario_red(usuario);
						newred.setId_rs_usuario(key);
						newred.setId_usuario(id_usuario);
						newred.setId_usuario_red(id_usuario);
						session.save(newred);
						salida.setCod_salida(0);
						salida.setDes_salida("inserto cod 2 y no existia");
					}else{
						
						String hql = "UPDATE rs_usuario set n_usuario_red = :salary "  + 
					             "WHERE id_usuario = :employee_id" +
								" and id_red_social = :id_red";
						Query query2 = session2.createQuery(hql);
						query2.setParameter("salary", usuario);
						query2.setParameter("employee_id", id_usuario);
						query2.setParameter("id_red", 2);
						int result = query2.executeUpdate();
						System.out.println("Filas Afectadas: " + result);
						
						salida.setCod_salida(0);
						salida.setDes_salida("Inserto cod 2 y existia con sin_info en sus datos");
						
					}
					
				}else if(id_red==3){
							idInsta=bloqueInstagram(usuario);
							key.setId_red_social(3);
							key.setN_usuario_red(usuario);
							newred.setId_rs_usuario(key);
							newred.setId_usuario(id_usuario);
							newred.setId_usuario_red(idInsta);
							session.save(newred);
							salida.setCod_salida(0);
							salida.setDes_salida("inserto cod 3");
						}
			}else{
				salida.setCod_salida(1);
				salida.setDes_salida("ya existente");
			}
			
			
		}catch(Exception ex){
			salida.setCod_salida(1);
			System.out.println(ex.getMessage());
			salida.setDes_salida("se cae :v");
			System.out.println("insertRedes :Excepcion Controlada");
		}
		session.getTransaction().commit();	
		session.close();
		session2.getTransaction().commit();	
		session2.close();
		return salida;
	}
	
	public int bloqueInstagram(String usuario){
		int insta=0;                                                                                   
		String searchURL ="https://api.instagram.com/v1/users/search?q=";
		String apiKey="&access_token=6394138.1677ed0.d1802576372c463bb3444075643c70d2";
		String toSearch = searchURL + usuario + apiKey;
		String result;
		
		try {
			URL url = new URL(toSearch);                                                               //entrega la url
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();                   //abre la conecion
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));//lee lo que esta en el buffer

			String line;
			StringBuffer buffer = new StringBuffer();                                                  //crea un objeto buffer
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			result=buffer.toString();                                                                  //se almacena en un buffer la info
			JSONObject resultj = new JSONObject(result);                                               //se carga un objeto de tipo json    
			//System.out.println("TODO EL JSON:"+resultj);
			//System.out.println("");
			//System.out.println("array de DATOS: "+resultj.getJSONArray("data"));
			JSONArray datosArray = resultj.getJSONArray("data");                                       //
			for(int i=0; i<datosArray.length();i++){
				//JSONObject nodoUser=new JSONObject(datosArray.getJSONObject(i));
				//System.out.println(nodoUser);
				//System.out.println(datosArray.getJSONObject(i).getString("username"));
				
				if(datosArray.getJSONObject(i).getString("username").equals(usuario)){
					insta=datosArray.getJSONObject(i).getInt("id");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("bloqueInstagram :Excepcion Controlada");
		}
		
		return insta;
		
	}
	
	public salidaStndr setredes(rs_usuario rsUser){
		
		salidaStndr salida=new salidaStndr();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			session.save(rsUser);
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallos");
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("setredes :Excepcion Controlada");
		}
		session.getTransaction().commit();	
		session.close();
		return salida;
	}
	
	public salidaStndr deleteredes(int id,int id_red){
		salidaStndr salida=new salidaStndr();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		try{
			//rs_usuario red = (rs_usuario) session.get(rs_usuario.class, id_red);
			query = session.createQuery("from rs_usuario where id_usuario="+id+" and id_red_social="+id_red);
			List<rs_usuario> p = (List<rs_usuario>) query.list();
			session.delete(p.get(0));
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallo");
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("deleteredes :Excepcion Controlada");
		}
		return salida;
	}
	
	//USUARIOS
	public ObtenerUsuario selectUser(int id){
		
		List<ObtenerRedxUser> oru=new ArrayList<ObtenerRedxUser>();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			Object u = session.load(usuario.class, id);
			usuario usuarioo=(usuario)u;
			
			Session session2 = sessionFactory.openSession();
			session2.beginTransaction();
			Query query;
			query = session2.createQuery("from rs_usuario where id_usuario="+id);
			List<rs_usuario> users = (List<rs_usuario>) query.list();
			
			for(rs_usuario use: users){
				ObtenerRedxUser ou=new ObtenerRedxUser();
				ou.setCodSalida(0);
				ou.setDes_salida("sin fallo");			
				ou.setId_red_social(use.getId_rs_usuario().getId_red_social());
				ou.setId_usuario(use.getId_usuario());
				ou.setId_usuario_red(use.getId_usuario_red());
				ou.setN_usuario_red(use.getId_rs_usuario().getN_usuario_red());
				oru.add(ou);
			}
			
			List<ObtenerPalabras> lp = selectWord(id); 
			ObtenerUsuario user = new ObtenerUsuario(usuarioo.getId_usuario(),usuarioo.getEstado(),usuarioo.getPerfil_usuario(),usuarioo.getNom1(),usuarioo.getNom2(),usuarioo.getApell1(),usuarioo.getApell2(),usuarioo.getEmp1(),usuarioo.getEmp2(),usuarioo.getCorreo(),usuarioo.getContraseña(),usuarioo.getKeyMovil(),oru,lp);
			session2.getTransaction().commit();	
			session2.close();
			return user;
			
		}catch(Exception ex){
			System.out.println("selectUser :Excepcion Controlada");
			//salida.setDes_salida(ex.getMessage());
		}
		session.getTransaction().commit();	
		session.close();
		return null;
	}
	
	public salidaStndr insertUser(String nom1,String nom2,String apell1,String apell2, String correo,String pass,String twit,String keyMovil)
	{
		salidaStndr salida=new salidaStndr();
		int count=0;
		
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		Session session3 = sessionFactory.openSession();
		session3.beginTransaction();
		Query query3;
		
		try{
			query3 = session3.createQuery("from usuario where correo='"+correo+"'");
			List<usuario> p = (List<usuario>) query3.list();
			//System.out.println("!!!!"+p.size());
			if(p.size()==0){
				System.out.println("insertUser: entra al crea usuario!");
				usuario user=new usuario();
				user.setNom1(nom1);
				user.setNom2(nom2);
				user.setApell1(apell1);
				user.setApell2(apell2);
				user.setCorreo(correo);
				user.setContraseña(pass);
				user.setEmp1("sin info");
				user.setEmp2("sin info");
				user.setPerfil_usuario(1);
				user.setEstado(1);
				user.setKeyMovil(keyMovil);
				
				try{
					System.out.println("insertUser :entra al try!");
					session.save(user);
					salida.setCod_salida(0);
					salida.setDes_salida("sin fallos");
					session.getTransaction().commit();	
					session.close();					
				}catch(Exception ex){
					salida.setCod_salida(1);
					salida.setDes_salida(ex.getMessage());
					System.out.println("insertUser :Excepcion Controlada p.size==0");
					
				}
				
				
				//session.getTransaction().commit();	
				//session.close();
				//insert Palabra
				//no esta implementado
				
				//insertRed
				
				Session session2 = sessionFactory.openSession();
				session2.beginTransaction();
				Query query2;
				query2 = session2.createQuery("from usuario where correo='"+correo+"' and contrasena='"+pass+"'");
				List<usuario> users = (List<usuario>) query2.list();
				salidaStndr salida2=new salidaStndr();
				rs_usuario rs=new rs_usuario();
				key_rs_usuarios key=new key_rs_usuarios(twit,2);//EN DURO PARA TWITTER
				rs.setId_usuario_red(users.get(0).getId_usuario());
				rs.setId_rs_usuario(key);
				rs.setId_usuario(users.get(0).getId_usuario());
				
				//BLOQUE BUSQUEDA GOOGLE
				//BloqueGoogle(nom1,apell1,users.get(0).getId_usuario());
				BloqueGoogle(nom1,apell1,7);
				
				salida2 =  setredes(rs);
				session2.getTransaction().commit();	
				session2.close();
				session3.getTransaction().commit();	
				session3.close();
			}else {
				salida.setCod_salida(1);
				System.out.println("insertUser :Excepcion Controlada ya existe!");
			}
		}catch(Exception ex){
			System.out.println("insertUser: principio !!");
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("insertUser :Excepcion Controlada");
		}
		return salida;
	}
	
	
	//BUSQUEDA GOOGLE
	final static String apiKey = "AIzaSyBsm97GWF2WLuAGGcyugUMAu2c4Pm27j_I";            //api de google
	final static String customSearchEngineKey = "007380653580648478702:j5liwfex5-m";   //key
	final static String searchURL = "https://www.googleapis.com/customsearch/v1?";     // url
	
	public static void BloqueGoogle(String nom,String apell,int id){
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		String wordsFimi="";
		
		query = session.createQuery("from palabras_fimi where tipo_palabra=1");
		List<palabras_fimi> p = (List<palabras_fimi>) query.list();
		
		for(palabras_fimi palabra: p){
			//System.out.print(palabra.getKey().getPalabra());
			wordsFimi=wordsFimi+ ""+ palabra.getKey().getPalabra();
		}
		String url = buildSearchString(nom+ " "+ apell+" "+wordsFimi, 1, 10);		
		String result = search(url);
		try {
			JSONObject resultJson = new JSONObject(result);
			JSONArray arrayData = new JSONArray(resultJson.getString("items"));
			for (int i = 0; i < arrayData.length(); i++)
	        {
				inserta(arrayData.getJSONObject(i),id);
	        }
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println("BloqueGoogle :Excepcion Controlada");
		}
		
		session.getTransaction().commit();	
		session.close();
	}
	
	public static void inserta(JSONObject jsonObj, int id){
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		String comentario;
		try{
			comentario=jsonObj.getString("snippet").replace("'", "");			
			key_historial_usuario id_historial =new key_historial_usuario(id,comentario);
			DateHandler fecha = new DateHandler();
			java.sql.Date sqlDate = new java.sql.Date(fecha.getDate().getTime());			
			historial_usuario hu=new historial_usuario();
			
			hu.setFecha(sqlDate);
			hu.setId_historial(id_historial);
			hu.setId_onombre_quien_comenta(jsonObj.getString("displayLink"));
			hu.setId_red_social(4);
			hu.setIs_falso_positivo(1);
			hu.setTipo_comentario("negativo");
			//sql = ("Insert into historial_usuario(id_usuario_red,id_red_social,comentario,tipo_comentario,fecha,id_onombre_quien_comenta,is_falso_positivo) values("+id+",4,'"+comentario+"','negativo',SYSDATE(),'"+jsonObj.getString("displayLink")+"',1 )");
			session.save(hu);
			session.getTransaction().commit();	
			session.close();
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println("inserta :Excepcion Controlada JSONException");
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			System.out.println("inserta :Excepcion Controlada Exception");
		}		
	}
	public static String search(String pUrl) {
		try {
			URL url = new URL(pUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("search :Excepcion Controlada ");
		}
		return null;
	}
	private static String buildSearchString(String searchString, int start, int numOfResults) {
		String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey + "&q=";

		// replace spaces in the search query with +
		String newSearchString = searchString.replace(" ", "%20");

		toSearch += newSearchString;

		// specify response format as json
		toSearch += "&alt=json";

		// specify starting result number
		toSearch += "&start=" + start;

		// specify the number of results you need from the starting position
		toSearch += "&num=" + numOfResults;

		System.out.println("Busqueda de la  URL: " + toSearch);
		return toSearch;
	}
	/*
	public String deleteUser(int delU){
		String salida="0";
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		usuario user = (usuario) session.get(usuario.class, delU);
		session.delete(user);
		
		session.getTransaction().commit();	
		session.close();
		
		return salida;
	}*/
	
	public salidaStndr modifUser(int id,String pass,String emp1,String emp2){
		salidaStndr salida=new salidaStndr();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			Object u = session.load(usuario.class, id);
			usuario user=(usuario)u;
			user.setEmp1(emp1);
			user.setEmp2(emp2);
			user.setContraseña(pass);
			session.update(user);
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallos");			
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("modifUser :Excepcion Controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		return salida;
	}
	
	//PALABRAS
	public salidaStndr insertWord(int id,String palabra){
		salidaStndr salida=new salidaStndr();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();	
		Session session3 = sessionFactory.openSession();
		session3.beginTransaction();	
		
		try{
			//key_palabras_usuario kpu=new key_palabras_usuario(palabra,1,id);
			//Object u = session2.load(palabras_usuario.class,kpu);
			//palabras_usuario existe=null;
			//existe=(palabras_usuario)u;
			//System.out.println("asd:"+existe);
			
			//conversion de String a UTF-8
			System.out.println(" insertWord palabra"+palabra);
			byte[] byteText = palabra.getBytes(Charset.forName("UTF-8"));
			String palabra_aux= new String(byteText , "UTF-8");
			palabra=palabra_aux;
			System.out.println(" insertWord v2_palabra_aux"+palabra_aux);
			
			Query query;
			query = session.createQuery("from palabras_usuario where id_usuario="+id+" and palabra='"+palabra+"' and tipo_palabra="+1);
			List<palabras_usuario> p = (List<palabras_usuario>) query.list();
			System.out.println(p.size());
			if(p.size()==0){
				key_palabras_usuario kpu = new key_palabras_usuario(palabra,1,id);
				palabras_usuario pu=new palabras_usuario(kpu);
			
				session3.save(pu);
				salida.setCod_salida(0);
				salida.setDes_salida("sin fallos");
			}else{
				salida.setCod_salida(1);
				salida.setDes_salida("palabra ya existe");
			}
			/*
			Object u = session.load(usuario.class, id);
			usuario user=(usuario)u;
			palabras=user.getPalabras();
			palabras= palabras+ " " + palabra;
			user.setPalabras(palabras);
			session.update(user);
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallos");*/
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("insertWord :Excepcion Controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		session2.getTransaction().commit();	
		session2.close();
		session3.getTransaction().commit();	
		session3.close();
		return salida;
	}
	/*
	public String deleteWord(int delW){
		String salida ="0";
			
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		palabras_usuario user = (palabras_usuario) session.get(palabras_usuario.class, delW);
		session.delete(user);
		
		session.getTransaction().commit();	
		session.close();
		
		return salida;
		
	}*/
	
	public List<ObtenerPalabras> selectWord(int id){
		List<ObtenerPalabras> lop=new ArrayList<ObtenerPalabras>();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		
		try{
			query = session.createQuery("from palabras_usuario where id_usuario="+id);
			List<palabras_usuario> p = (List<palabras_usuario>) query.list();
			if(p.size()>0){
				for(palabras_usuario obtPal: p){
					ObtenerPalabras u=new ObtenerPalabras();
					u.setCod_salida(0);
					u.setDes_salida("sin fallo");			
					u.setTipo_palabra(obtPal.getId_palabras_usuarios().getTipo_palabra());
					u.setId_usuario(obtPal.getId_palabras_usuarios().getId());
					u.setPalabra(obtPal.getId_palabras_usuarios().getPalabra());
					
					lop.add(u);
				}
			
			}else{
				ObtenerPalabras x=new ObtenerPalabras();
				x.setCod_salida(1);
				x.setDes_salida("no tiene palabras indexadas");			
				x.setTipo_palabra(0);
				x.setId_usuario(0);
				x.setPalabra(" ");
				
				lop.add(x);
				
			}
			
		}catch(Exception ex){
			ObtenerPalabras x=new ObtenerPalabras();
			x.setCod_salida(1);
			x.setDes_salida(ex.getMessage());			
			x.setTipo_palabra(0);
			x.setId_usuario(0);
			x.setPalabra(" ");
			lop.add(x);
			System.out.println("selectWord :Excepcion Controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		
		return lop;
	}
	
	
	//HISTORIAL
	public List<ObtenerHistorial> selectAll100(int id){
		List<ObtenerHistorial> oh=new ArrayList<ObtenerHistorial>();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Integer> idu=new ArrayList<Integer>(); 
		idu = getidU(id);
		String idBus="0";
		for(int i=0;i<idu.size();i++){
			idBus=idBus+","+String.valueOf(idu.get(i));
		}
		idBus="("+idBus+")";
		Query query1;
		
		try{
			int max=100;
			int i=0;
			query1 = session.createQuery("from historial_usuario where id_usuario_red in "+idBus+" order by fecha DESC");//+" and id_red_social="+rsU.getId_red_social());
			List<historial_usuario> hu = (List<historial_usuario>) query1.list();
				
				for(historial_usuario h:hu){
					if(i<max){	
						ObtenerHistorial o=new ObtenerHistorial();
						key_historial_usuario k=new key_historial_usuario();
						
						o.setComentario(h.getId_historial().getComentario());
						o.setId_usuario_red(h.getId_historial().getId_usuario_red());
						o.setCod_salida(0);
						o.setDes_salida("sin fallo");
					
							o.setFecha(h.getFecha());
						
						o.setId_onombre_quien_comenta(h.getId_onombre_quien_comenta());
						//o.setComentario(h.getComentario());
						o.setId_red_social(h.getId_red_social());
						//o.setId_usuario_red(h.getId_usuario_red());
						
							o.setIs_falso_positivo(h.getIs_falso_positivo());
						
						o.setTipo_comentario(h.getTipo_comentario());
						oh.add(o);
						i++;
					}
			}
		}catch(Exception ex){
			ObtenerHistorial x=new ObtenerHistorial();
			x.setCod_salida(1);
			x.setDes_salida(ex.getMessage());
			oh.add(x);	
			System.out.println("selectAll100 :Excepcion Controlada ");
		}
		
		
		session.getTransaction().commit();	
		session.close();
	
		return oh;
	}
	public List<ObtenerHistorial> selectPositivo(int id){
		List<ObtenerHistorial> oh=new ArrayList<ObtenerHistorial>();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();


		List<Integer> idu=new ArrayList<Integer>(); 
		idu = getidU(id);
		String idBus="0";
		for(int i=0;i<idu.size();i++){
			idBus=idBus+","+String.valueOf(idu.get(i));
		}
		idBus="("+idBus+")";
		
		
		Query query1;
		
		try{
			int max=10;
			int i=0;
			query1 = session.createQuery("from historial_usuario where id_usuario_red  in "+idBus+" order by fecha DESC");//+" and id_red_social="+rsU.getId_red_social());
			List<historial_usuario> hu = (List<historial_usuario>) query1.list();
				
				for(historial_usuario h:hu){
					if(i<max){	
						ObtenerHistorial o=new ObtenerHistorial();
						key_historial_usuario k=new key_historial_usuario();
						
						o.setComentario(h.getId_historial().getComentario());
						o.setId_usuario_red(h.getId_historial().getId_usuario_red());
						o.setCod_salida(0);
						o.setDes_salida("sin fallo");
					
							o.setFecha(h.getFecha());
						
						o.setId_onombre_quien_comenta(h.getId_onombre_quien_comenta());
						//o.setComentario(h.getComentario());
						o.setId_red_social(h.getId_red_social());
						//o.setId_usuario_red(h.getId_usuario_red());
						
							o.setIs_falso_positivo(h.getIs_falso_positivo());
						
						o.setTipo_comentario(h.getTipo_comentario());
						oh.add(o);
						i++;
					}
			}
		}catch(Exception ex){
			ObtenerHistorial x=new ObtenerHistorial();
			x.setCod_salida(1);
			x.setDes_salida(ex.getMessage());
			oh.add(x);	
			System.out.println("selectPositivo :Excepcion Controlada ");
		}
		
		
		session.getTransaction().commit();	
		session.close();
	
		return oh;
	}
	
	public List<Integer> getidU(int id){
		List<Integer> idu=new ArrayList<Integer>();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query1;
		try{
			query1 = session.createQuery("from rs_usuario where id_usuario="+id);
			List<rs_usuario> rs=(List<rs_usuario>)query1.list();
			for(rs_usuario rsU:rs){
				idu.add(rsU.getId_usuario_red());
			}
			
		}catch(Exception ex){
			idu=null;
			System.out.println("getidU :Excepcion Controlada ");
			
		}
		
		return idu;
	}
	
	
	//MANTENEDOR NOTICIAS
	public salidaStndr insertNoticia(String seccion,String contenido,String titulo, String foto, int tipo,int estado,Date fecha) throws UnsupportedEncodingException{
		salidaStndr salida=new salidaStndr();
		
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println("inicio Insercion de Noticias ");
		System.out.println("Contenido :"+contenido);
		ByteBuffer contenidoaux= Charset.forName("UTF-8").encode(contenido);
		System.out.println("Contenidoaux :"+contenidoaux);
		System.out.println(" fin Insercion de Noticias ");
		
		
		
		byte[] byteText = contenido.getBytes(Charset.forName("UTF-8"));
		String contenido_aux= new String(byteText , "UTF-8");
		contenido=contenido_aux;
		System.out.println("v2 contenido_aux :"+contenido_aux);
		
       //String original = "hello world";
       //byte[] utf8Bytes = original.getBytes("UTF-8");
       
		byte[] byteText2 = titulo.getBytes(Charset.forName("UTF-8"));
		String titulo_aux= new String(byteText2 , "UTF-8");
		titulo=titulo_aux;
		System.out.println("v2 titulo_aux :"+titulo_aux);
		
		noticias n =new noticias(seccion,contenido,titulo,foto,tipo,estado,fecha);
		try{
			session.save(n);
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallos");
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("insertNoticia :Excepcion Controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		
			
		return salida;
	}
	
	public List<ObtenerNoticia> selectNoticias(){
		List<ObtenerNoticia> lon=new ArrayList<ObtenerNoticia>();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query1;
		try{
			query1 = session.createQuery("from noticias order by fecha DESC");
			List<noticias> rs=(List<noticias>)query1.list();
			if(rs.size()>0){
				for(noticias rsU:rs){
					//idu=rsU.getId_usuario_red();
					ObtenerNoticia n=new ObtenerNoticia();
					n.setCod_salida(0);
					n.setDes_salida("sin fallos");
					n.setContenido(rsU.getContenido());
					n.setEstado(rsU.getEstado());
					n.setFecha(rsU.getFecha());
					n.setFoto(rsU.getFoto());
					n.setId(rsU.getId());
					n.setSeccion(rsU.getSeccion());
					n.setTipo_noticia(rsU.getTipo_noticia());
					n.setTitulo(rsU.getTitulo());
					lon.add(n);
				}
			}else{
				ObtenerNoticia n=new ObtenerNoticia();
				n.setCod_salida(1);
				n.setDes_salida("sin noticias");
				n.setContenido("");
				n.setEstado(0);
				n.setFecha(null);
				n.setFoto("");
				n.setId(0);
				n.setSeccion("");
				n.setTipo_noticia(0);
				n.setTitulo("");
				lon.add(n);
			}
			session.getTransaction().commit();	
			session.close();
			return lon;
			
		}catch(Exception ex){
			ObtenerNoticia n=new ObtenerNoticia();
			n.setCod_salida(1);
			n.setDes_salida(ex.getMessage());
			n.setContenido("");
			n.setEstado(0);
			n.setFecha(null);
			n.setFoto("");
			n.setId(0);
			n.setSeccion("");
			n.setTipo_noticia(0);
			n.setTitulo("");
			lon.add(n);
			System.out.println("selectNoticias :Excepcion Controlada ");
			return lon;
			
		}
		
		
	}
	//public salidaStndr modifNoticia(){
		
	//}
	public salidaStndr updateNoticiaEstado(int id,int estado){
		salidaStndr salida =new salidaStndr();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try{
			Object u = session.load(noticias.class, id);
			noticias n=(noticias)u;
			n.setEstado(estado);		
			session.update(n);
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallos");
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("updateNoticiaEstado :Excepcion Controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		return salida;	
	}
	
	
	//USER_ADMIN
	public List<ObtenerUserAdmin> selectAllUserAdmin(){
		List<ObtenerUserAdmin> salida=new ArrayList<ObtenerUserAdmin>();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query;
		try{
		query = session.createQuery("from admin_user");
		List<admin_user> p = (List<admin_user>) query.list();
		if(p.size()>0){
			for(admin_user u:p){
				ObtenerUserAdmin newU = new ObtenerUserAdmin(0,"sin fallos",u.getCorreo(),u.getNombre(),u.getContrasena(),u.getEstado());
				salida.add(newU);
			}
		}else{
			ObtenerUserAdmin newU = new ObtenerUserAdmin(1,"no existen admins"," "," "," ",0);
			salida.add(newU);
		}
		}catch(Exception ex){
			ObtenerUserAdmin newU = new ObtenerUserAdmin(1,ex.getMessage()," "," "," ",0);
			salida.add(newU);
			System.out.println("selectAllUserAdmin :Excepcion Controlada ");
		}
		session.getTransaction().commit();	
		session.close();
		return salida;
	}
	
	public ObtenerUserAdmin selectUserAdmin(String correo){
		ObtenerUserAdmin salida=new ObtenerUserAdmin();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		Query query;
		Query query2;
		
		try{
			query2 = session2.createQuery("from admin_user where correo='"+correo+"'");
			List<admin_user> p = (List<admin_user>) query2.list();
			if(p.size()>0){
				salida.setEstado(p.get(0).getEstado());
			}else{
				salida.setEstado(0);
			}
			
			query = session.createQuery("from usuario where correo='"+correo+"'");
			List<usuario> users = (List<usuario>) query.list();
			
			if(users.size()>0){
				
				salida.setCod_salida(0);
				salida.setDes_salida("sin fallos");	
				salida.setContrasena(users.get(0).getContraseña());
				salida.setCorreo(users.get(0).getCorreo());
				salida.setNombre(users.get(0).getNom1()+" "+users.get(0).getApell1());
			
			}else{
				salida.setCod_salida(1);
				salida.setDes_salida("no existe");
				salida.setContrasena(" ");
				salida.setCorreo(" ");
				salida.setNombre(" ");
			}
			
					
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			salida.setContrasena(" ");
			salida.setCorreo(" ");
			salida.setNombre(" ");
			System.out.println("selectUserAdmin :Excepcion Controlada ");
		
		}
		
		session.getTransaction().commit();	
		session.close();
		return salida;
	}
	public salidaStndr insertUserAdmin(String correo,String contrasena,String nombre,int estado){
		salidaStndr salida =new salidaStndr();
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Session session3 = sessionFactory.openSession();
		session3.beginTransaction();
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		Query query3;
		Query query2;
		
		try{
			query2 = session2.createQuery("from user where correo='"+correo+"'");
			List<usuario> lu = (List<usuario>) query2.list();
			if(lu.size()>0){
				query3 = session3.createQuery("from admin_user where correo='"+correo+"'");
				List<admin_user> p = (List<admin_user>) query3.list();
				
				if(p.size()==0){
					admin_user user=new admin_user(correo,contrasena,nombre,estado);
					session.save(user);
					salida.setCod_salida(0);
					salida.setDes_salida("sin fallos");
				}else{
					salida.setCod_salida(1);
					salida.setDes_salida("Administrador ya existente");
				}
			}else {
				salida.setCod_salida(1);
				salida.setDes_salida("Usuario no existente");
			}	
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("insertUserAdmin :Excepcion Controlada ");
		}
		
		session.getTransaction().commit();	
		session.close();
		session3.getTransaction().commit();	
		session3.close();
		session2.getTransaction().commit();	
		session2.close();
		return salida;
	}
	public salidaStndr modifUser(String correo,int estado){
		salidaStndr salida =new salidaStndr();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try{
			Object u = session.load(admin_user.class, correo);
			admin_user user=(admin_user)u;
			user.setEstado(estado);			
			session.update(user);
			salida.setCod_salida(0);
			salida.setDes_salida("sin fallos");			
		}catch(Exception ex){
			salida.setCod_salida(1);
			salida.setDes_salida(ex.getMessage());
			System.out.println("modifUser :Excepcion Controlada ");
			
		}
		
		session.getTransaction().commit();	
		session.close();
		
		return salida;
	}
	
	
}
