package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.RequestHoliday;
import com.giantLink.RH.entities.RequestStatus;
import com.giantLink.RH.enums.State;
import com.giantLink.RH.mappers.RequestHolidayMapper;
import com.giantLink.RH.models.request.RequestHolidayRequest;
import com.giantLink.RH.models.response.RequestHolidayResponse;
import com.giantLink.RH.models.response.SuccessResponse;
import com.giantLink.RH.repositories.EmployeeRepository;
import com.giantLink.RH.repositories.HolidayBalanceRepository;
import com.giantLink.RH.repositories.RequestHolidayRepository;
import com.giantLink.RH.repositories.RequestStatusRepository;
import com.giantLink.RH.services.RequestHolidayService;
import com.giantLink.RH.services.RequestStatusService;
import com.sun.net.httpserver.Authenticator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestHolidayServiceImpl implements RequestHolidayService
{
    @Autowired
    private RequestHolidayRepository requestHolidayRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RequestStatusService requestStatusService;
    @Autowired
    private RequestStatusRepository requestStatusRepository;
    @Autowired
    private HolidayBalanceRepository holidayBalanceRepository;

    @Override
    public RequestHolidayResponse add(RequestHolidayRequest requestHolidayRequest)
    {
        RequestHoliday requestHoliday = RequestHolidayMapper.INSTANCE.requestToEntity(requestHolidayRequest);
        Optional<Employee> findEmployee = employeeRepository.findById(requestHolidayRequest.getEmployee_id());
        if (!findEmployee.isPresent())
            throw new RuntimeException("Employee having id : " + requestHolidayRequest.getEmployee_id().toString() + " is not found !");

        RequestStatus requestStatus = RequestStatus.builder()
                .type(State.PENDING)
                .request(requestHoliday)
                .build();

        requestStatusRepository.save(requestStatus);
        requestHoliday.setStatus(requestStatus);
        requestHoliday.setEmployee(findEmployee.get());
        requestHolidayRepository.save(requestHoliday);

        RequestHolidayResponse response =  RequestHolidayMapper.INSTANCE.entityToResponse(requestHoliday);

        return response;
    }

    @Override
    public List<RequestHolidayResponse> get()
    {
         return RequestHolidayMapper.INSTANCE.listToResponseList(requestHolidayRepository.findAll());
    }

    @Override
    public RequestHolidayResponse update(RequestHolidayRequest holidayUpdate, Long request_id)
    {
        RequestHoliday existingRequestHoliday = requestHolidayRepository.findById(request_id)
                .orElseThrow(() -> new RuntimeException("Request Holiday not found with ID: " + request_id));
        existingRequestHoliday.setNumberOfDays(holidayUpdate.getNumberOfDays());
        existingRequestHoliday.setStartDate(holidayUpdate.getStartDate());
        existingRequestHoliday.setFinishDate(holidayUpdate.getFinishDate());

        RequestHoliday updatedRequestHoliday = requestHolidayRepository.save(existingRequestHoliday);

        return RequestHolidayMapper.INSTANCE.entityToResponse(updatedRequestHoliday);
    }

    @Override
    public void delete(Long request_id)
    {
        Optional<RequestHoliday> existingRequestHoliday = requestHolidayRepository.findById(request_id);
        if(existingRequestHoliday.isPresent())
            requestHolidayRepository.deleteById(request_id);
        else
            throw new RuntimeException("Request Holiday not found with ID: " + request_id);
    }
    @Override
    public RequestHolidayResponse get(Long request_id)
    {
        Optional<RequestHoliday> findRequest = requestHolidayRepository.findById(request_id);
        if (!findRequest.isPresent())
            throw new RuntimeException("Request Holiday having id : " + request_id + " is not found !");
        else
            return RequestHolidayMapper.INSTANCE.entityToResponse(findRequest.get());
    }
    @Override
    public SuccessResponse addRequestHoliday(RequestHolidayRequest requestHolidayRequest)
    {

        RequestHoliday requestHoliday = RequestHolidayMapper.INSTANCE.requestToEntity(requestHolidayRequest);
        Optional<Employee> findEmployee = employeeRepository.findById(requestHolidayRequest.getEmployee_id());

        if (!findEmployee.isPresent())
            throw new RuntimeException("Employee having id : " + requestHolidayRequest.getEmployee_id().toString() + " is not found !");
        
        RequestStatus requestStatus = RequestStatus.builder()
                .type(State.PENDING)
                .request(requestHoliday)
                .build();

        requestStatusRepository.save(requestStatus);
        requestHoliday.setStatus(requestStatus);
        requestHoliday.setEmployee(findEmployee.get());
        requestHolidayRepository.save(requestHoliday);

        SuccessResponse successResponse = SuccessResponse.builder()
                .message("Your request was send successfully")
                .build();

        return successResponse;
    }
    @Override
    public SuccessResponse drop(Long request_id)
    {
        RequestHoliday existingRequestHoliday = requestHolidayRepository.findById(request_id)
                .orElseThrow(() -> new RuntimeException("Request Holiday having ID: " + request_id + " not found "));
        System.out.println(request_id);
        requestHolidayRepository.deleteById(request_id);

        return SuccessResponse.builder()
                .message("Deleted successfully")
                .build();
    }
    @Override
    public List<RequestHolidayResponse> getAllRequestHolidaysByIdEmployee(Long employee_id)
    {
        List<RequestHoliday> requestHolidays = requestHolidayRepository.findByEmployeeId(employee_id);
        return RequestHolidayMapper.INSTANCE.listToResponseList(requestHolidays);
    }

}
