package ro.ucv.ace.neo4jworkshop.controller;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.neo4jworkshop.model.Company;
import ro.ucv.ace.neo4jworkshop.model.Post;
import ro.ucv.ace.neo4jworkshop.model.User;
import ro.ucv.ace.neo4jworkshop.repository.CompanyRepository;
import ro.ucv.ace.neo4jworkshop.repository.HelperNeo4jRepository;
import ro.ucv.ace.neo4jworkshop.repository.PostRepository;
import ro.ucv.ace.neo4jworkshop.repository.UserRepository;

@RestController
@RequestMapping("/hello")
public class HelloController {

  @Autowired
  private HelperNeo4jRepository helperNeo4jRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PostRepository postRepository;

  @GetMapping
  public String hello(@RequestParam(value = "name") String name) {
    helperNeo4jRepository.deleteGraph();

    User calin = new User();
    calin.setUuid(1);
    calin.setName("Calin");
    userRepository.save(calin);

    User mihai = new User();
    mihai.setUuid(2);
    mihai.setName("Mihai");
    userRepository.save(mihai);

    User emilian = new User();
    emilian.setUuid(3);
    emilian.setName("Emilian");
    userRepository.save(emilian);

    User stefan = new User();
    stefan.setUuid(4);
    stefan.setName("Stefan");
    userRepository.save(stefan);

    User vladucu = new User();
    vladucu.setUuid(5);
    vladucu.setName("Vladucu");
    userRepository.save(vladucu);

    User mihaela = new User();
    mihaela.setUuid(6);
    mihaela.setName("Mihaela");
    userRepository.save(mihaela);

    User valentina = new User();
    valentina.setUuid(7);
    valentina.setName("Valentina");
    userRepository.save(valentina);

    User theodora = new User();
    theodora.setUuid(8);
    theodora.setName("Theodora");
    userRepository.save(theodora);

    User andra = new User();
    andra.setUuid(9);
    andra.setName("Andra");
    userRepository.save(andra);

    User adelina = new User();
    adelina.setUuid(10);
    adelina.setName("Adelina");
    userRepository.save(adelina);

    User felix = new User();
    felix.setUuid(11);
    felix.setName("Felix");
    userRepository.save(felix);

    User razvan = new User();
    razvan.setUuid(12);
    razvan.setName("Razvan");
    userRepository.save(razvan);

    calin.addFriend(valentina, vladucu, mihaela, mihai, emilian, felix, andra, adelina, theodora, stefan, razvan);
    userRepository.save(calin);

    valentina.addFriend(calin, felix, vladucu, emilian, razvan);
    userRepository.save(valentina);

    felix.addFriend(calin, valentina, vladucu, razvan);
    userRepository.save(felix);

    vladucu.addFriend(valentina, felix, mihai, emilian, stefan, mihaela, razvan, calin);
    userRepository.save(vladucu);

    mihai.addFriend(calin, vladucu, emilian, razvan, stefan);
    userRepository.save(mihai);

    mihaela.addFriend(stefan, calin, vladucu);
    userRepository.save(mihaela);

    theodora.addFriend(adelina, andra, calin);
    userRepository.save(theodora);

    andra.addFriend(theodora, adelina, calin);
    userRepository.save(andra);

    adelina.addFriend(theodora, andra, calin);
    userRepository.save(adelina);

    razvan.addFriend(calin, vladucu, mihai, valentina, felix, stefan, mihaela);
    userRepository.save(razvan);

    stefan.addFriend(calin, razvan, mihai, mihaela);
    userRepository.save(stefan);

    emilian.addFriend(valentina, mihai, vladucu, calin);
    userRepository.save(emilian);

    Company endava = new Company();
    endava.setUuid(1);
    endava.setName("Endava");
    companyRepository.save(endava);

    Company iquest = new Company();
    iquest.setUuid(2);
    iquest.setName("IQuest");
    companyRepository.save(iquest);

    Company bigdataro = new Company();
    bigdataro.setUuid(3);
    bigdataro.setName("Bigdata.ro");
    companyRepository.save(bigdataro);

    Company ucv = new Company();
    ucv.setUuid(4);
    ucv.setName("Universitatea din Craiova");
    companyRepository.save(ucv);

    Company netrom = new Company();
    netrom.setUuid(5);
    netrom.setName("Netrom");
    companyRepository.save(netrom);

    Company db = new Company();
    db.setUuid(6);
    db.setName("Deutsche Bank");
    companyRepository.save(db);

    endava.setEmployees(Sets.newHashSet(calin, emilian, adelina, theodora, andra));
    companyRepository.save(endava);

    iquest.setEmployees(Sets.newHashSet(mihai, vladucu));
    companyRepository.save(iquest);

    bigdataro.setEmployees(Sets.newHashSet(valentina, felix));
    companyRepository.save(bigdataro);

    ucv.setEmployees(Sets.newHashSet(stefan));
    companyRepository.save(ucv);

    netrom.setEmployees(Sets.newHashSet(mihaela));
    companyRepository.save(netrom);

    db.setEmployees(Sets.newHashSet(razvan));
    companyRepository.save(db);

    Post calinPost1 = new Post();
    calinPost1.setUuid(1);
    calinPost1.setPoster(calin);
    calinPost1.setContent("My first post ever!");
    postRepository.save(calinPost1);

    Post calinPost2 = new Post();
    calinPost2.setUuid(2);
    calinPost2.setPoster(calin);
    calinPost2.setContent("I'm at the Neo4j Workshop!");
    postRepository.save(calinPost2);
    postRepository.findByPoster_Name("Calin");

    mihai.likePost(calinPost1, calinPost2);
    userRepository.save(mihai);

    return "Hello " + name + "!";
  }

}
