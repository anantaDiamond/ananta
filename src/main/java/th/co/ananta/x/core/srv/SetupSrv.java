package th.co.ananta.x.core.srv;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.BranchEmail;
import th.co.ananta.x.core.domain.CaratSize;
import th.co.ananta.x.core.domain.ExchangeRate;
import th.co.ananta.x.core.repo.IBranchEmailRepository;
import th.co.ananta.x.core.repo.ICaratSizeRepository;
import th.co.ananta.x.core.repo.IExchangeRateRepository;
import th.co.ananta.x.web.base.XException;

public class SetupSrv {
	
	private static final Logger log = LoggerFactory.getLogger(SetupSrv.class);
	ICaratSizeRepository cRepo;
	IExchangeRateRepository eRepo;
	IBranchEmailRepository bRepo;
	
	public boolean update(CaratSize carat, ExchangeRate rate, BranchEmail email) throws XException {
		int result=0;
		try {
			if (!StringUtils.isBlank(carat.getMax()) || !StringUtils.isBlank(carat.getMin())) {
				result = cRepo.update(carat);
			}
		
			if (null != rate.getRate()) {
				result = eRepo.create(rate.getRate());
			}
			
			if (null != email && null != email.getBranchEmail()) {
				bRepo.create(email);
			}
		} catch (XException e) {
			throw e;
		}
	
		return result==1?true:false;
	}

	public ICaratSizeRepository getcRepo() {
		return cRepo;
	}

	public void setcRepo(ICaratSizeRepository cRepo) {
		this.cRepo = cRepo;
	}

	public IExchangeRateRepository geteRepo() {
		return eRepo;
	}

	public void seteRepo(IExchangeRateRepository eRepo) {
		this.eRepo = eRepo;
	}

	public IBranchEmailRepository getbRepo() {
		return bRepo;
	}

	public void setbRepo(IBranchEmailRepository bRepo) {
		this.bRepo = bRepo;
	}
	
}
