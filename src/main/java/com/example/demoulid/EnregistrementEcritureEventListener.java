package com.example.demoulid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
public class EnregistrementEcritureEventListener {

    @Autowired
    EcritureJGRepository repository;

    /** réouvre une toute nouvelle transaction
        https://blog.pragmatists.com/spring-events-and-transactions-be-cautious-bdb64cb49a95
     */
    @Transactional(propagation = REQUIRES_NEW)
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleCustom(EnregistrementEcritureEvent event) {
        System.out.println("Handling event after a transaction AFTER COMMIT.");

        // récupère l'ecriture
        Long idEcriture = event.getIdEcriture();
        Optional<EcritureJournalGeneral> maybeEjg = repository.findById(idEcriture);

        EcritureJournalGeneral ejg = maybeEjg.get();

        List<EcritureJournalGeneral> ejgs = repository.findAllByRegieAndAnneeOrderByUlidAsc(ejg.getRegie(), ejg.getAnnee());

        for(int i = 0; i < ejgs.size(); i++) {
            if(ejgs.get(i).getId() == ejg.getId()) {
                ejg.setNumeroJG(i+1);
            }
        }

        repository.save(ejg);
        System.out.println(ejg);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnregistrementEcritureEvent {
        // évenement d'enregistrement d'une Ecriture
        Long idEcriture;
    }
}
