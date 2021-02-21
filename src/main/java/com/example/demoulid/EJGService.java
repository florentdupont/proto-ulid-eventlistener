package com.example.demoulid;

import com.example.demoulid.EnregistrementEcritureEventListener.EnregistrementEcritureEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.github.f4b6a3.ulid.UlidCreator.getMonotonicUlid;

@Transactional
@Service
public class EJGService {

    @Autowired
    EcritureJGRepository repository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void something() {

        EcritureJournalGeneral e = null;

        e = new EcritureJournalGeneral();
        e.setAnnee(2020);
        e.setDate(LocalDateTime.now());
        e.setRegie("BERLIN");
        e.setType("DEMANDE_APPRO");
        e.setUlid(getMonotonicUlid().toString());
        e = repository.save(e);
        System.out.println("AJOUT " + e);
        applicationEventPublisher.publishEvent(new EnregistrementEcritureEvent(e.getId()));


        e = new EcritureJournalGeneral();
        e.setAnnee(2020);
        e.setDate(LocalDateTime.now());
        e.setRegie("BERLIN");
        e.setType("DROIT_CHANCELLERIE");
        e.setUlid(getMonotonicUlid().toString());
        e=repository.save(e);
        System.out.println("AJOUT " + e);
        applicationEventPublisher.publishEvent(new EnregistrementEcritureEvent(e.getId()));


        e = new EcritureJournalGeneral();
        e.setAnnee(2021);
        e.setDate(LocalDateTime.now());
        e.setRegie("BERLIN");
        e.setType("DEMANDE_APPRO");
        e.setUlid(getMonotonicUlid().toString());
        e=repository.save(e);
        System.out.println("AJOUT " + e);
        applicationEventPublisher.publishEvent(new EnregistrementEcritureEvent(e.getId()));

        e = new EcritureJournalGeneral();
        e.setAnnee(2021);
        e.setDate(LocalDateTime.now());
        e.setRegie("BERLIN");
        e.setType("DEMANDE_APPRO");
        e.setUlid(getMonotonicUlid().toString());
        e=repository.save(e);
        System.out.println("AJOUT " + e);
        applicationEventPublisher.publishEvent(new EnregistrementEcritureEvent(e.getId()));

        e = new EcritureJournalGeneral();
        e.setAnnee(2021);
        e.setDate(LocalDateTime.now());
        e.setRegie("BERLIN");
        e.setType("DEMANDE_APPRO");
        e.setUlid(getMonotonicUlid().toString());
        e=repository.save(e);
        System.out.println("AJOUT " + e);
        applicationEventPublisher.publishEvent(new EnregistrementEcritureEvent(e.getId()));

        e = new EcritureJournalGeneral();
        e.setAnnee(2020);
        e.setDate(LocalDateTime.now());
        e.setRegie("BERLIN");
        e.setType("DEMANDE_APPRO");
        e.setUlid(getMonotonicUlid().toString());
        e=repository.save(e);
        System.out.println("AJOUT " + e);
        applicationEventPublisher.publishEvent(new EnregistrementEcritureEvent(e.getId()));

        // récupère la liste des Ecriture pour une Régie et Une année
        // elle est récupérée ordonnée par ULID

        // les ecriture sont récupérées par Regie et par année, puis triée dans l'ordre des ulid
        // ce qui nous assure de les récupérer dans l'ordre
//        List<EcritureJournalGeneral> ejgs = repository.findAllByRegieAndAnneeOrderByUlidAsc("BERLIN", 2020);


        // peu importe s'il y'a des trous, on re-séquence ici
//        for(int i=0; i < ejgs.size(); i++) {
//            ejgs.get(i).setNumeroJG(i+1);
//        }

        /*
         partir sur un simple "int" que l'on incrémente ?
         Le problème c'est que si 2 Ecriture sont réalisées en même temps sur une Regie, alors chaque process
         va lire la dernière valeur disponible et l'incrémenter. Ce qui fait qu'au moment de l'écriture, on peut tout
         à fait se retrouver avec 2 valeurs qui vont être identique.
         Il ne faut donc pas setter le numeroJG au moment de la création, mais seulement après.
         C'est la raison pour laquelle c'était effectué en 2 fois :
         - Une lecture dans la vue via numeroJgCalculé
         - un setNumeroJG une fois que l'enregistrement est effectif pour être sur de l'ordre


        Utiliser seulement les "date" ?
        On a vu que c'est problématique pour les Ecarts, car les date sont des dateAppelation, l'ordre n'est pas assuré?


        Utiliser des séquence ?
        - les remettre à zéro est possible mais pas "naturel"
        - nécessité d'avoir autant de séquence que de couple Année/Régie


        Le numéro ne doit plus changer : Une fois l'écriture réalisée, alors le numéro est "fixé".
        l'ecriture ne peut pas être supprimée et donc aucun "trou" ne sera possible.

        Seule exception, les "Ecarts" qui peuvent être supprimés en fin de mois.


        Pour les écarts, c'est la fermeture de la journée comptable qui va créer les écarts

        */



//        System.out.println(ejgs);

    }
}
