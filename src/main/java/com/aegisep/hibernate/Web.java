package com.aegisep.hibernate;

import com.aegisep.hibernate.vo.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class Web {

    private static final Logger log = LoggerFactory.getLogger(Web.class);

    @GetMapping("/getPerson")
    @CrossOrigin(origins = "http://127.0.0.1")
    public ModelAndView web(Model model) {
        log.info(" >>>>>>>>>>>>>>>> hello world <<<<<<<<<<<<<<<<<<< ");
        model.addAttribute("service", "web");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Person p = session.find(Person.class, Long.parseLong("1"));
        log.info("  >>>>>>> p >>>>>>  " + p);

        Person insert = new Person();
        insert.setFirstname("Shanone");
        insert.setLastname("Perterson");

        session.saveOrUpdate(insert);

        //where person_id =:id
        Query<Person> query = session.createQuery("From Person ", Person.class);
//        query.setParameter("id", Long.parseLong("1"));

        List<Person> empList = query.list();

        log.info("  >>>>>>> empList >>>>>>  " + empList);
//
//        for (Person emp: empList) {
//            log.info("  >>>>>> person >>>>>>>  " + emp);
//        }

        model.addAttribute("personList", empList);

        ModelAndView mv = new ModelAndView();
        mv.addObject("service", "web");
        mv.addObject("personList", empList);
        mv.setViewName("index::#main");

        return mv;
    }

}
