package cn.damon.web;

import cn.damon.model.Employee;
import cn.damon.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MessageController
 * @Description
 * @Author Damon
 * @Date 2019/7/1 9:06
 * @Email zdmsjyx@163.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RabbitMQSender sender;

    @GetMapping("/msg")
    public String sendMsg(@RequestParam() String name,String employeeId){
        Employee employee = new Employee();
        employee.setEmpName(name);
        employee.setEmpId(employeeId);

        sender.send(employee);
        return "send successfully";
    }
}
