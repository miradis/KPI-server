package com.program.service;

import java.util.List;
import com.program.model.Status;
import com.program.exception.StatusException;


public interface StatusService {

	  List<Status>  getAllStatus() throws StatusException;

	  Status addNewStatus(Integer categoryId,Status status)throws StatusException;

	  Status getStatusById(Integer Id)throws StatusException;

	  Status updateStatusById(Integer id, Status status) throws StatusException;

	  void deleteStatusById(Integer Id) throws StatusException;

}
