package ro.ucv.ace.neo4jworkshop.controller;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.neo4jworkshop.model.*;
import ro.ucv.ace.neo4jworkshop.model.relationship.Like;
import ro.ucv.ace.neo4jworkshop.repository.*;

@Slf4j
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

  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ReactionTypeRepository reactionTypeRepository;

  @GetMapping
  public String hello(@RequestParam(value = "name") String name) {
    helperNeo4jRepository.deleteGraph();

    User calin = new User();
    calin.setUuid(1);
    calin.setName("Calin");
    userRepository.save(calin);

    log.info("Found user with UUID '1', having Name: " + userRepository.findByUuid(1).getName());
    log.info("Found user with Name 'Calin', having UUID: " + userRepository.findByName("Calin").getUuid());

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

    log.info("Number of Posts written by User with Name 'Calin': " + postRepository.findByPoster_Name("Calin").size());

    Like mihaiLikesCalinPost1 = new Like();
    mihaiLikesCalinPost1.setUser(mihai);
    mihaiLikesCalinPost1.setPost(calinPost1);
    mihaiLikesCalinPost1.setTimestamp(System.currentTimeMillis());

    Like mihaiLikesCalinPost2 = new Like();
    mihaiLikesCalinPost2.setUser(mihai);
    mihaiLikesCalinPost2.setPost(calinPost2);
    mihaiLikesCalinPost2.setTimestamp(System.currentTimeMillis());

    mihai.likePost(mihaiLikesCalinPost1, mihaiLikesCalinPost2);
    userRepository.save(mihai);

    log.info("Number of Likes given by User with Name 'Mihai': " + likeRepository.findByUser_Name("Mihai").size());

    Comment valentinaComment1CalinPost1 = new Comment();
    valentinaComment1CalinPost1.setUuid(1);
    valentinaComment1CalinPost1.setCommenter(valentina);
    valentinaComment1CalinPost1.setPost(calinPost1);
    valentinaComment1CalinPost1.setContent("You're so cool!");
    commentRepository.save(valentinaComment1CalinPost1);

    Comment mihaiComment1CalinPost1 = new Comment();
    mihaiComment1CalinPost1.setUuid(2);
    mihaiComment1CalinPost1.setCommenter(mihai);
    mihaiComment1CalinPost1.setPost(calinPost2);
    mihaiComment1CalinPost1.setContent("We're so fortunate to be part of this!");
    commentRepository.save(mihaiComment1CalinPost1);

    Comment vladucuComment1CalinPost2 = new Comment();
    vladucuComment1CalinPost2.setUuid(3);
    vladucuComment1CalinPost2.setCommenter(vladucu);
    vladucuComment1CalinPost2.setPost(calinPost2);
    vladucuComment1CalinPost2.setContent("Best workshop EVER!");
    commentRepository.save(vladucuComment1CalinPost2);

    Comment mihaiComment2CalinPost1 = new Comment();
    mihaiComment2CalinPost1.setUuid(4);
    mihaiComment2CalinPost1.setCommenter(mihai);
    mihaiComment2CalinPost1.setPost(calinPost2);
    mihaiComment2CalinPost1.setContent("Mind blowing !!!11oneone11");
    commentRepository.save(mihaiComment2CalinPost1);

    Comment stefanComment1CalinPost2 = new Comment();
    stefanComment1CalinPost2.setUuid(5);
    stefanComment1CalinPost2.setCommenter(stefan);
    stefanComment1CalinPost2.setPost(calinPost2);
    stefanComment1CalinPost2.setContent("To think I almost missed it...");
    commentRepository.save(stefanComment1CalinPost2);

    log.info("Number of Comments on Post with UUID '2': " + commentRepository.findByPost_Uuid(2).size());

    ReactionType loveReactionType = new ReactionType();
    loveReactionType.setUuid(1);
    loveReactionType.setName("Love");
    reactionTypeRepository.save(loveReactionType);

    ReactionType sadReactionType = new ReactionType();
    sadReactionType.setUuid(2);
    sadReactionType.setName("Sad");
    reactionTypeRepository.save(sadReactionType);

    return "Hello " + name + "!";
  }

}
