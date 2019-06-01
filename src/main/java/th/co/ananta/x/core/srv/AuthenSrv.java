package th.co.ananta.x.core.srv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.repo.IAuthorizeRepository;
import th.co.ananta.x.core.repo.ILoginHistoryRepository;
import th.co.ananta.x.web.base.XException;

public class AuthenSrv {

	private static final Logger log = LoggerFactory.getLogger(AuthenSrv.class);
	IAuthorizeRepository authRep;
	ILoginHistoryRepository loginRep;
	
	public boolean authorize(Agent agent) throws XException {
		log.info("authorize");
		boolean result = false;
		try {
			int auth = authRep.get(agent);
			if (auth == 1) {
				loginRep.create(agent);
				result = true;
			} 
		} catch (XException e) {
			throw e;
		}
		
		return result;
	}

	public void setAuthRep(IAuthorizeRepository authRep) {
		this.authRep = authRep;
	}

	public void setLoginRep(ILoginHistoryRepository loginRep) {
		this.loginRep = loginRep;
	}
	
	
}
