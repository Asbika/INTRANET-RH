package com.giantLink.RH.services;

import com.giantLink.RH.models.response.SuccessResponse;

import java.util.List;

public interface CrudService <RQ,RS,ET,ID>
{
	RS add(RQ request);
	List<RS> get();
	RS update(RQ request, ID id);
	void delete(ID id);
	RS get(ID id);

//	List<RS> getRequestHolidayByEmployeeId(ID id);
}
