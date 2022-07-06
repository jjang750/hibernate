package com.aegisep.hibernate;

import com.aegisep.hibernate.vo.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView web(@RequestParam(value="requestPage") int requestPage) {
        log.info(" >>>>>>>>>>>>>>>> hello world <<<<<<<<<<<<<<<<<<< ");

        int numPerPage = 10;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Person p = session.find(Person.class, Long.parseLong("1"));
        log.info("  >>>>>>> p >>>>>>  " + p);

//        Person insert = new Person();
//        insert.setFirstname("Shanone");
//        insert.setLastname("Perterson");
//
//        session.saveOrUpdate(insert);

        //where person_id =:id
        Query<Person> query = session.createQuery("From Person  order by person_id desc ", Person.class);
//        query.setParameter("id", Long.parseLong("1"));

        int totalSize = query.list().size();

        log.info("  >>>>>>> empList  totalSize >>>>>>  " + totalSize);

        query.setFirstResult((requestPage-1) * numPerPage);
        query.setMaxResults(numPerPage);

        List<Person> empList = query.list();

        log.info("  >>>>>>> empList >>>>>>  " + empList.size());

        ModelAndView mv = new ModelAndView();
        mv.addObject("service", "web");
        mv.addObject("totalSize", totalSize);
        mv.addObject("personList", empList);
        mv.setViewName("index::#main");

        return mv;
    }

}
