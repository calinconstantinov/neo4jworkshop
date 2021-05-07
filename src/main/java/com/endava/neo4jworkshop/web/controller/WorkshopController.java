package com.endava.neo4jworkshop.web.controller;

import com.endava.neo4jworkshop.model.*;
import com.endava.neo4jworkshop.model.relationship.Like;
import com.endava.neo4jworkshop.model.time.Day;
import com.endava.neo4jworkshop.repository.*;
import com.endava.neo4jworkshop.repository.relationship.LikeRepository;
import com.endava.neo4jworkshop.service.TimeService;
import com.endava.neo4jworkshop.service.TimeSetupService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/hello")
public class WorkshopController {

    HelperNeo4jRepository helperNeo4jRepository;

    UserRepository userRepository;

    AuthenticationRepository authenticationRepository;

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

        var calin = new User();
        calin.setUuid("1");
        calin.setName("Calin");
        calin = userRepository.save(calin);

        log.info("Found user with Uuid '1', having Name: {}",
                userRepository.findByUuid("1")
                        .getName());
        log.info("Found user with Name 'Calin', having Uuid: {}",
                userRepository
                        .findByName("Calin")
                        .getUuid());

        var calinAuthentication = new Authentication();
        calinAuthentication.setUser(calin);
        calinAuthentication.setEmail("foo@bar.com");
        calinAuthentication.setPassword("Passw0rd");
        authenticationRepository.save(calinAuthentication);

        log.info("Found Authentication for User with name 'Calin': {}", authenticationRepository.findByUser_Name("Calin"));
        log.info("User 'Calin' does not reflect Authentication assignment: {}", calin.getAuthentication());
        calin = userRepository.findByName("Calin");
        log.info("User 'Calin' now does reflect Authentication assignment: {}", calin.getAuthentication());

        var mihai = new User();
        mihai.setName("Mihai");
        mihai = userRepository.save(mihai);

        var emilian = new User();
        emilian.setName("Emilian");
        emilian = userRepository.save(emilian);

        var stefan = new User();
        stefan.setName("Stefan");
        stefan = userRepository.save(stefan);

        var vladucu = new User();
        vladucu.setName("Vladucu");
        vladucu = userRepository.save(vladucu);

        var mihaela = new User();
        mihaela.setName("Mihaela");
        mihaela = userRepository.save(mihaela);

        var valentina = new User();
        valentina.setName("Valentina");
        valentina = userRepository.save(valentina);

        var theodora = new User();
        theodora.setName("Theodora");
        theodora = userRepository.save(theodora);

        var andra = new User();
        andra.setName("Andra");
        andra = userRepository.save(andra);

        var adelina = new User();
        adelina.setName("Adelina");
        adelina = userRepository.save(adelina);

        var felix = new User();
        felix.setName("Felix");
        felix = userRepository.save(felix);

        var razvan = new User();
        razvan.setName("Razvan");
        razvan = userRepository.save(razvan);

        var maria = new User();
        maria.setName("Maria");
        maria = userRepository.save(maria);

        var viorel = new User();
        viorel.setName("Viorel");
        viorel = userRepository.save(viorel);

        var ines = new User();
        ines.setName("Ines");
        ines = userRepository.save(ines);

        var oana = new User();
        oana.setName("Oana");
        oana = userRepository.save(oana);

        var lucian = new User();
        lucian.setName("Lucian");
        lucian = userRepository.save(lucian);

        var radu = new User();
        radu.setName("Radu");
        radu = userRepository.save(radu);

        var adrian = new User();
        adrian.setName("Adrian");
        adrian = userRepository.save(adrian);

        var madalina = new User();
        madalina.setName("Madalina");
        madalina = userRepository.save(madalina);

        calin.addFriend(valentina, vladucu, mihaela, mihai, emilian, felix, andra, adelina, theodora,
                stefan, razvan, maria, viorel, ines, oana, lucian, radu, adrian, madalina);
        calin = userRepository.save(calin);

        valentina.addFriend(calin, felix, vladucu, emilian, razvan);
        valentina = userRepository.save(valentina);

        felix.addFriend(calin, valentina, vladucu, razvan);
        felix = userRepository.save(felix);

        vladucu.addFriend(valentina, felix, mihai, emilian, stefan, mihaela, razvan, calin);
        vladucu = userRepository.save(vladucu);

        mihai.addFriend(calin, vladucu, emilian, razvan, stefan);
        mihai = userRepository.save(mihai);

        mihaela.addFriend(stefan, calin, vladucu);
        mihaela = userRepository.save(mihaela);

        theodora.addFriend(adelina, andra, calin);
        theodora = userRepository.save(theodora);

        andra.addFriend(theodora, adelina, calin);
        andra = userRepository.save(andra);

        adelina.addFriend(theodora, andra, calin);
        adelina = userRepository.save(adelina);

        razvan.addFriend(calin, vladucu, mihai, valentina, felix, stefan, mihaela);
        razvan = userRepository.save(razvan);

        stefan.addFriend(calin, razvan, vladucu, mihai, mihaela);
        stefan = userRepository.save(stefan);

        emilian.addFriend(valentina, mihai, vladucu, calin);
        emilian = userRepository.save(emilian);

        var endava = new Company();
        endava.setName("Endava");
        companyRepository.save(endava);

        var nagarro = new Company();
        nagarro.setName("Nagarro");
        companyRepository.save(nagarro);

        var deloitteDigital = new Company();
        deloitteDigital.setName("Deloitte Digital");
        companyRepository.save(deloitteDigital);

        var universityOfCraiova = new Company();
        universityOfCraiova.setName("University of Craiova");
        companyRepository.save(universityOfCraiova);

        var sap = new Company();
        sap.setName("SAP");
        companyRepository.save(sap);

        var deutscheBank = new Company();
        deutscheBank.setName("Deutsche Bank");
        companyRepository.save(deutscheBank);

        endava.setEmployees(Set.of(calin, emilian, adelina, theodora, andra));
        companyRepository.save(endava);

        nagarro.setEmployees(Set.of(mihai, vladucu));
        companyRepository.save(nagarro);

        deloitteDigital.setEmployees(Set.of(valentina, felix));
        companyRepository.save(deloitteDigital);

        universityOfCraiova.setEmployees(Set.of(stefan));
        companyRepository.save(universityOfCraiova);

        sap.setEmployees(Set.of(mihaela));
        companyRepository.save(sap);

        deutscheBank.setEmployees(Set.of(razvan));
        companyRepository.save(deutscheBank);

        log.info("Found Companies related to Company with name 'Endava': {}",
                companyRepository.findFriendsOfEmployeesCompanies("Endava"));

        var calinPost1 = new Post();
        calinPost1.setPoster(calin);
        calinPost1.setContent("My first post ever!");
        postRepository.save(calinPost1);

        var calinPost2 = new Post();
        calinPost2.setPoster(calin);
        calinPost2.setContent("I'm at the Neo4j Workshop!");
        postRepository.save(calinPost2);

        log.info("Number of Posts written by User with Name 'Calin': {}",
                postRepository
                        .findByPoster_Name("Calin")
                        .size());

        var calinLikesCalinPost1 = new Like();
        calinLikesCalinPost1.setUser(calin);
        calinLikesCalinPost1.setPost(calinPost1);
        calinLikesCalinPost1.setTimestamp(System.currentTimeMillis());
        calin.likePost(calinLikesCalinPost1);
        calin = userRepository.save(calin);

        Like mihaiLikesCalinPost1 = new Like();
        mihaiLikesCalinPost1.setUser(mihai);
        mihaiLikesCalinPost1.setPost(calinPost1);
        mihaiLikesCalinPost1.setTimestamp(System.currentTimeMillis());

        Like mihaiLikesCalinPost2 = new Like();
        mihaiLikesCalinPost2.setUser(mihai);
        mihaiLikesCalinPost2.setPost(calinPost2);
        mihaiLikesCalinPost2.setTimestamp(System.currentTimeMillis());

        mihai.likePost(mihaiLikesCalinPost1, mihaiLikesCalinPost2);
        mihai = userRepository.save(mihai);

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
