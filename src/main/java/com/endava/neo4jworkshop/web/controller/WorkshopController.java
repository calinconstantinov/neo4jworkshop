package com.endava.neo4jworkshop.web.controller;

import com.endava.neo4jworkshop.model.*;
import com.endava.neo4jworkshop.model.relationship.Like;
import com.endava.neo4jworkshop.model.time.Day;
import com.endava.neo4jworkshop.repository.*;
import com.endava.neo4jworkshop.service.TimeService;
import com.endava.neo4jworkshop.service.TimeSetupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkshopController {

    Session session;

    HelperNeo4jRepository helperNeo4jRepository;

    UserRepository userRepository;

    CredentialsRepository credentialsRepository;

    CompanyRepository companyRepository;

    PostRepository postRepository;

    LikeRepository likeRepository;

    CommentRepository commentRepository;

    ReactionTypeRepository reactionTypeRepository;

    ReactionRepository reactionRepository;

    TimeSetupService timeSetupService;

    TimeService timeService;

    @GetMapping
    public String hello() {
        helperNeo4jRepository.deleteData();

        //this would be a another way of cleaning the database.
        //session.purgeDatabase();

        var calin = new User();
        calin.setUuid("1");
        calin.setName("Calin");
        userRepository.save(calin);

        log.info("Found user with Uuid '1', having Name: {}",
                userRepository.findByUuid("1")
                        .getName());
        log.info("Found user with Name 'Calin', having Uuid: {}",
                userRepository
                        .findByName("Calin")
                        .getUuid());

        var calinCredentials = new Credentials();
        calinCredentials.setUser(calin);
        calinCredentials.setEmail("foo@bar.com");
        calinCredentials.setPassword("Passw0rd");
        credentialsRepository.save(calinCredentials);

        //this clears the mapping context
        session.clear();
        log.info("Found Credentials for User with name 'Calin': {}", credentialsRepository.findByUser_Name("Calin"));

        userRepository.save(calin);
        //refresh the entity
        calin = userRepository.findByName("Calin");
        log.info("Now found Credentials for User with name 'Calin' {}", credentialsRepository.findByUser_Name("Calin"));

        calinCredentials.setUser(calin);
        credentialsRepository.save(calinCredentials);
        log.info("After retrying, found Credentials for User with name 'Calin': {}",
                credentialsRepository.findByUser_Name("Calin"));
        log.info("Refreshed User 'Calin' now reflects credential assignment: {}", calin.getCredentials());

        User mihai = new User();
        mihai.setName("Mihai");
        userRepository.save(mihai);

        User emilian = new User();
        emilian.setName("Emilian");
        userRepository.save(emilian);

        User stefan = new User();
        stefan.setName("Stefan");
        userRepository.save(stefan);

        User vladucu = new User();
        vladucu.setName("Vladucu");
        userRepository.save(vladucu);

        User mihaela = new User();
        mihaela.setName("Mihaela");
        userRepository.save(mihaela);

        User valentina = new User();
        valentina.setName("Valentina");
        userRepository.save(valentina);

        User theodora = new User();
        theodora.setName("Theodora");
        userRepository.save(theodora);

        User andra = new User();
        andra.setName("Andra");
        userRepository.save(andra);

        User adelina = new User();
        adelina.setName("Adelina");
        userRepository.save(adelina);

        User felix = new User();
        felix.setName("Felix");
        userRepository.save(felix);

        User razvan = new User();
        razvan.setName("Razvan");
        userRepository.save(razvan);

        User maria = new User();
        maria.setName("Maria");
        userRepository.save(maria);

        User viorel = new User();
        viorel.setName("Viorel");
        userRepository.save(viorel);

        User ines = new User();
        ines.setName("Ines");
        userRepository.save(ines);

        User oana = new User();
        oana.setName("Oana");
        userRepository.save(oana);

        User lucian = new User();
        lucian.setName("Lucian");
        userRepository.save(lucian);

        User raduT = new User();
        raduT.setName("RaduT");
        userRepository.save(raduT);

        User raduD = new User();
        raduD.setName("RaduD");
        userRepository.save(raduD);

        User madalina = new User();
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
        endava.setName("Endava");
        companyRepository.save(endava);

        Company iquest = new Company();
        iquest.setName("IQuest");
        companyRepository.save(iquest);

        Company bigdataro = new Company();
        bigdataro.setName("Bigdata.ro");
        companyRepository.save(bigdataro);

        Company ucv = new Company();
        ucv.setName("Universitatea din Craiova");
        companyRepository.save(ucv);

        Company netrom = new Company();
        netrom.setName("Netrom");
        companyRepository.save(netrom);

        Company db = new Company();
        db.setName("Deutsche Bank");
        companyRepository.save(db);

        endava.setEmployees(Set.of(calin, emilian, adelina, theodora, andra));
        companyRepository.save(endava);

        iquest.setEmployees(Set.of(mihai, vladucu));
        companyRepository.save(iquest);

        bigdataro.setEmployees(Set.of(valentina, felix));
        companyRepository.save(bigdataro);

        ucv.setEmployees(Set.of(stefan));
        companyRepository.save(ucv);

        netrom.setEmployees(Set.of(mihaela));
        companyRepository.save(netrom);

        db.setEmployees(Set.of(razvan));
        companyRepository.save(db);

        log.info("Found Companies related to Company with name 'Endava': {}",
                companyRepository.findFriendsOfEmployeesCompanies("Endava"));

        Post calinPost1 = new Post();
        calinPost1.setPoster(calin);
        calinPost1.setContent("My first post ever!");
        postRepository.save(calinPost1);

        Post calinPost2 = new Post();
        calinPost2.setPoster(calin);
        calinPost2.setContent("I'm at the Neo4j Workshop!");
        postRepository.save(calinPost2);

        log.info("Number of Posts written by User with Name 'Calin': {}",
                postRepository
                        .findByPoster_Name("Calin")
                        .size());

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

        log.info("Number of Likes given by User with Name 'Mihai': {}",
                likeRepository
                        .findByUser_Name("Mihai")
                        .size());

        Comment valentinaComment1CalinPost1 = new Comment();
        valentinaComment1CalinPost1.setUuid("11");
        valentinaComment1CalinPost1.setCommenter(valentina);
        valentinaComment1CalinPost1.setContent("You're so cool!");
        calinPost1.setComments(Set.of(valentinaComment1CalinPost1));
        postRepository.save(calinPost1);

        Comment mihaiComment1CalinPost1 = new Comment();
        mihaiComment1CalinPost1.setCommenter(mihai);
        mihaiComment1CalinPost1.setContent("We're so fortunate to be part of this!");
        commentRepository.save(mihaiComment1CalinPost1);

        Comment vladucuComment1CalinPost2 = new Comment();
        vladucuComment1CalinPost2.setCommenter(vladucu);
        vladucuComment1CalinPost2.setContent("Best workshop EVER!");
        commentRepository.save(vladucuComment1CalinPost2);

        Comment mihaiComment2CalinPost1 = new Comment();
        mihaiComment2CalinPost1.setCommenter(mihai);
        mihaiComment2CalinPost1.setContent("Mind blowing !!!11oneone11");
        commentRepository.save(mihaiComment2CalinPost1);

        Comment stefanComment1CalinPost2 = new Comment();
        stefanComment1CalinPost2.setCommenter(stefan);
        stefanComment1CalinPost2.setContent("To think I almost missed it...");
        commentRepository.save(stefanComment1CalinPost2);

        Comment calinComment1CalinPost2 = new Comment();
        calinComment1CalinPost2.setCommenter(calin);
        calinComment1CalinPost2.setContent("Glad you could make it!");
        commentRepository.save(calinComment1CalinPost2);

        calinPost2.setComments(Set.of(
                mihaiComment1CalinPost1, vladucuComment1CalinPost2, mihaiComment2CalinPost1,
                stefanComment1CalinPost2, calinComment1CalinPost2
        ));
        postRepository.save(calinPost2);

        log.info("Number of Comments by User with Name 'Mihai': {}",
                commentRepository
                        .findByCommenter_Name("Mihai")
                        .size());

        ReactionType loveReactionType = new ReactionType();
        loveReactionType.setName("LOVE");
        reactionTypeRepository.save(loveReactionType);

        ReactionType hahaReactionType = new ReactionType();
        hahaReactionType.setName("HAHA");
        reactionTypeRepository.save(hahaReactionType);

        Reaction emilianLoveValentinaComment1CalinPost1 = new Reaction();
        emilianLoveValentinaComment1CalinPost1.setReactionType(loveReactionType);
        emilianLoveValentinaComment1CalinPost1.setReactingUser(emilian);
        emilianLoveValentinaComment1CalinPost1.setComment(valentinaComment1CalinPost1);
        reactionRepository.save(emilianLoveValentinaComment1CalinPost1);

        Reaction calinHahaStefanComment1CalinPost2 = new Reaction();
        calinHahaStefanComment1CalinPost2.setReactionType(hahaReactionType);
        calinHahaStefanComment1CalinPost2.setReactingUser(calin);
        calinHahaStefanComment1CalinPost2.setComment(stefanComment1CalinPost2);
        reactionRepository.save(calinHahaStefanComment1CalinPost2);

        Reaction mihaelaLoveValentinaComment1CalinPost1 = new Reaction();
        mihaelaLoveValentinaComment1CalinPost1.setReactionType(loveReactionType);
        mihaelaLoveValentinaComment1CalinPost1.setReactingUser(mihaela);
        mihaelaLoveValentinaComment1CalinPost1.setComment(valentinaComment1CalinPost1);
        reactionRepository.save(mihaelaLoveValentinaComment1CalinPost1);

        Reaction razvanLoveCalinComment1CalinPost2 = new Reaction();
        razvanLoveCalinComment1CalinPost2.setReactionType(loveReactionType);
        razvanLoveCalinComment1CalinPost2.setReactingUser(razvan);
        razvanLoveCalinComment1CalinPost2.setComment(calinComment1CalinPost2);
        reactionRepository.save(razvanLoveCalinComment1CalinPost2);

        log.info("Found Reactions of type 'LOVE': {}",
                reactionRepository
                        .findByReactionType_Name("LOVE")
                        .size());
        log.info("Found Reactions for Comment with Uuid '11': {}",
                reactionRepository
                        .findByComment_Uuid("11")
                        .size());

        timeSetupService.setupTime();
        Day day = timeService.find(2021, 5, 15);
        log.info("Day {} has {} Hours", day.getUuid(), day.getHours().size());

        Comment calinReplyVladucuComment1CalinPost2 = new Comment();
        calinReplyVladucuComment1CalinPost2.setCommenter(calin);
        calinReplyVladucuComment1CalinPost2.setHour(timeService.find(2021, 5, 16, 9));
        calinReplyVladucuComment1CalinPost2.setContent("Thanks! <3");
        vladucuComment1CalinPost2.addReply(calinReplyVladucuComment1CalinPost2);
        commentRepository.save(vladucuComment1CalinPost2);

        Comment emilianReplyStefanComment1CalinPost2 = new Comment();
        emilianReplyStefanComment1CalinPost2.setCommenter(emilian);
        emilianReplyStefanComment1CalinPost2.setHour(timeService.find(2021, 5, 16, 10));
        emilianReplyStefanComment1CalinPost2.setContent("That would've sucked haha");
        stefanComment1CalinPost2.addReply(emilianReplyStefanComment1CalinPost2);
        commentRepository.save(emilianReplyStefanComment1CalinPost2);

        return "Done!";
    }
}
