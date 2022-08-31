package com.humanresource.service;

import com.humanresource.model.Employee;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 29/08/2022 - 06:25
 */
public interface EmailService {
    void sendMail(Employee employee, boolean isTerminationMail);
}