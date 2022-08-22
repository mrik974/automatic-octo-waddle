package fr.occidevops;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WhoamiApplication {
	
	@Value("${fr.occidevops.add-to-response:Hello}")
	String addStringToResponse;

	public static void main(String[] args) {
		SpringApplication.run(WhoamiApplication.class, args);
	}
	
	@GetMapping("/")
	public String whoami(HttpSession session) {
		String hostname;
		String ip;
		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		    ip = addr.getHostAddress();
		}
		catch (UnknownHostException ex)
		{
		    hostname = "Hostname can not be resolved";
		    ip = "Ip can not be resolved";
		}
		StringBuilder builder = new StringBuilder("<html>");
		builder.append("<header><title>"+addStringToResponse+"</title></header>");
		builder.append("<body>");
		builder.append(addStringToResponse);
		builder.append("<br/>");
		builder.append("Hostname : ");
		builder.append(hostname);
		builder.append("<br/>");
		builder.append("Ip : ");
		builder.append(ip);
		builder.append("<br/>");
		builder.append("Session : ");
		builder.append(session.getId());
		builder.append("</body>");
		builder.append("</html>");
		return builder.toString();
	}

}
