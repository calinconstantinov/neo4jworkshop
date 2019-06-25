package ro.ucv.ace.neo4jworkshop.controller;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.neo4jworkshop.model.*;
import ro.ucv.ace.neo4jworkshop.model.relationship.Like;
import ro.ucv.ace.neo4jworkshop.model.time.Day;
import ro.ucv.ace.neo4jworkshop.repository.*;
import ro.ucv.ace.neo4jworkshop.service.ExternalIdBuilderService;
import ro.ucv.ace.neo4jworkshop.service.TimeSetupService;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

  @Autowired
  private Session session;

  @Autowired
  private HelperNeo4jRepository helperNeo4jRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CredentialsRepository credentialsRepository;

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

  @Autowired
  private ReactionRepository reactionRepository;

  @Autowired
  private TimeSetupService timeSetupService;

  @Autowired
  private ExternalIdBuilderService externalIdBuilderService;

  @Autowired
  private DayRepository dayRepository;

  @Autowired
  private HourRepository hourRepository;

  @GetMapping
  public String hello() {
    helperNeo4jRepository.deleteGraph();

    //this would be a another way of cleaning the database.
    //session.purgeDatabase();

    User calin = new User();
    calin.setUuid(1);
    calin.setName("Calin");
    userRepository.save(calin);

    log.info("Found user with UUID '1', having Name: " + userRepository.findByUuid(1).getName());
    log.info("Found user with Name 'Calin', having UUID: " + userRepository.findByName("Calin").getUuid());

    Credentials calinCredentials = new Credentials();
    calinCredentials.setUuid(1);
    calinCredentials.setUser(calin);
    calinCredentials.setEmail("foo@bar.com");
    calinCredentials.setPassword("Passw0rd");
    credentialsRepository.save(calinCredentials);

    //this clears the mapping context
    session.clear();
    log.info("Found Credentials for User with name 'Calin': " + credentialsRepository.findByUser_Name("Calin"));

    userRepository.save(calin);
    //refresh the entity
    calin = userRepository.findByName("Calin");
    log.info("Now found Credentials for User with name 'Calin': " + credentialsRepository.findByUser_Name("Calin"));

    calinCredentials.setUser(calin);
    credentialsRepository.save(calinCredentials);
    log.info("After retrying, found Credentials for User with name 'Calin': " + credentialsRepository
        .findByUser_Name("Calin"));
    log.info("Refreshed User 'Calin' now reflects credential assignment: " + calin.getCredentials());

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

    User maria = new User();
    maria.setUuid(13);
    maria.setName("Maria");
    userRepository.save(maria);

    User viorel = new User();
    viorel.setUuid(14);
    viorel.setName("Viorel");
    userRepository.save(viorel);

    User ines = new User();
    ines.setUuid(15);
    ines.setName("Ines");
    userRepository.save(ines);

    User oana = new User();
    oana.setUuid(16);
    oana.setName("Oana");
    userRepository.save(oana);

    User lucian = new User();
    lucian.setUuid(17);
    lucian.setName("Lucian");
    userRepository.save(lucian);

    User raduT = new User();
    raduT.setUuid(18);
    raduT.setName("RaduT");
    userRepository.save(raduT);

    User raduD = new User();
    raduD.setUuid(19);
    raduD.setName("RaduD");
    userRepository.save(raduD);

    User madalina = new User();
    madalina.setUuid(20);
    madalina.setName("Madalina");
    userRepository.save(madalina);

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

    stefan.addFriend(calin, razvan, vladucu, mihai, mihaela);
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

    log.info("Found Companies related to Company with name 'Endava': " + companyRepository
        .findFriendsOfEmployeesCompanies("Endava"));

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

    log.info("Number of Posts written by User with Name 'Calin': " + postRepository.findByPoster_Name("Calin").size());

    Like calinLikesCalinPost1 = new Like();
    calinLikesCalinPost1.setUser(calin);
    calinLikesCalinPost1.setPost(calinPost1);
    calinLikesCalinPost1.setTimestamp(System.currentTimeMillis());
    calin.likePost(calinLikesCalinPost1);
    userRepository.save(calin);

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
    valentinaComment1CalinPost1.setContent("You're so cool!");
    calinPost1.setComments(Sets.newHashSet(valentinaComment1CalinPost1));
    postRepository.save(calinPost1);

    Comment mihaiComment1CalinPost1 = new Comment();
    mihaiComment1CalinPost1.setUuid(2);
    mihaiComment1CalinPost1.setCommenter(mihai);
    mihaiComment1CalinPost1.setContent("We're so fortunate to be part of this!");

    Comment vladucuComment1CalinPost2 = new Comment();
    vladucuComment1CalinPost2.setUuid(3);
    vladucuComment1CalinPost2.setCommenter(vladucu);
    vladucuComment1CalinPost2.setContent("Best workshop EVER!");

    Comment mihaiComment2CalinPost1 = new Comment();
    mihaiComment2CalinPost1.setUuid(4);
    mihaiComment2CalinPost1.setCommenter(mihai);
    mihaiComment2CalinPost1.setContent("Mind blowing !!!11oneone11");

    Comment stefanComment1CalinPost2 = new Comment();
    stefanComment1CalinPost2.setUuid(5);
    stefanComment1CalinPost2.setCommenter(stefan);
    stefanComment1CalinPost2.setContent("To think I almost missed it...");

    Comment calinComment1CalinPost2 = new Comment();
    calinComment1CalinPost2.setUuid(6);
    calinComment1CalinPost2.setCommenter(calin);
    calinComment1CalinPost2.setContent("Glad you could make it!");

    calinPost2.setComments(Sets.newHashSet(mihaiComment1CalinPost1, vladucuComment1CalinPost2, mihaiComment2CalinPost1,
        stefanComment1CalinPost2, calinComment1CalinPost2));
    postRepository.save(calinPost2);

    log.info("Number of Comments by User with Name 'Mihai': " + commentRepository.findByCommenter_Name("Mihai").size());

    ReactionType loveReactionType = new ReactionType();
    loveReactionType.setUuid(1);
    loveReactionType.setName("Love");
    reactionTypeRepository.save(loveReactionType);

    ReactionType hahaReactionType = new ReactionType();
    hahaReactionType.setUuid(2);
    hahaReactionType.setName("Haha");
    reactionTypeRepository.save(hahaReactionType);

    Reaction emilianLoveValentinaComment1CalinPost1 = new Reaction();
    emilianLoveValentinaComment1CalinPost1.setUuid(1);
    emilianLoveValentinaComment1CalinPost1.setType(loveReactionType);
    emilianLoveValentinaComment1CalinPost1.setReacter(emilian);
    emilianLoveValentinaComment1CalinPost1.setComment(valentinaComment1CalinPost1);
    reactionRepository.save(emilianLoveValentinaComment1CalinPost1);

    Reaction calinHahaStefanComment1CalinPost2 = new Reaction();
    calinHahaStefanComment1CalinPost2.setUuid(2);
    calinHahaStefanComment1CalinPost2.setType(hahaReactionType);
    calinHahaStefanComment1CalinPost2.setReacter(calin);
    calinHahaStefanComment1CalinPost2.setComment(stefanComment1CalinPost2);
    reactionRepository.save(calinHahaStefanComment1CalinPost2);

    Reaction mihaelaLoveValentinaComment1CalinPost1 = new Reaction();
    mihaelaLoveValentinaComment1CalinPost1.setUuid(3);
    mihaelaLoveValentinaComment1CalinPost1.setType(loveReactionType);
    mihaelaLoveValentinaComment1CalinPost1.setReacter(mihaela);
    mihaelaLoveValentinaComment1CalinPost1.setComment(valentinaComment1CalinPost1);
    reactionRepository.save(mihaelaLoveValentinaComment1CalinPost1);

    Reaction razvanLoveCalinComment1CalinPost2 = new Reaction();
    razvanLoveCalinComment1CalinPost2.setUuid(4);
    razvanLoveCalinComment1CalinPost2.setType(loveReactionType);
    razvanLoveCalinComment1CalinPost2.setReacter(razvan);
    razvanLoveCalinComment1CalinPost2.setComment(calinComment1CalinPost2);
    reactionRepository.save(razvanLoveCalinComment1CalinPost2);

    log.info("Found Reactions of type 'Love': " + reactionRepository.findByType_Name("Love").size());
    log.info("Found Reactions for Commet with UUID '1': " + reactionRepository.findByComment_Uuid(1).size());

    timeSetupService.setupTime();
    Day day = dayRepository.findByUuid(externalIdBuilderService.buildDayExternalId(2019, 3, 15));
    log.info("Day " + day.getUuid() + " has " + day.getHours().size() + " Hours");

    Comment calinReplyVladucuComment1CalinPost2 = new Comment();
    calinReplyVladucuComment1CalinPost2.setUuid(7);
    calinReplyVladucuComment1CalinPost2.setCommenter(calin);
    calinReplyVladucuComment1CalinPost2
        .setHour(hourRepository.findByUuid(externalIdBuilderService.buildHourExternalId(2019, 3, 16, 9)));
    calinReplyVladucuComment1CalinPost2.setContent("Thanks! <3");
    vladucuComment1CalinPost2.addReply(calinReplyVladucuComment1CalinPost2);
    commentRepository.save(vladucuComment1CalinPost2);

    Comment emilianReplyStefanComment1CalinPost2 = new Comment();
    emilianReplyStefanComment1CalinPost2.setUuid(8);
    emilianReplyStefanComment1CalinPost2.setCommenter(emilian);
    emilianReplyStefanComment1CalinPost2
        .setHour(hourRepository.findByUuid(externalIdBuilderService.buildHourExternalId(2019, 3, 16, 10)));
    emilianReplyStefanComment1CalinPost2.setContent("That would've sucked haha");
    stefanComment1CalinPost2.addReply(emilianReplyStefanComment1CalinPost2);
    commentRepository.save(emilianReplyStefanComment1CalinPost2);

    return "Done!";
  }

}
