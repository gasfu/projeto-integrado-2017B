package services;

import java.util.ArrayList;

import models.Evaluation;
import repositories.EvaluationsRepository;;

public class EvaluationService {
	
	public Evaluation create(Evaluation evaluation) {
		EvaluationsRepository repository = new EvaluationsRepository();
		return repository.create(evaluation);
	}
	
	public ArrayList<Evaluation> getByLocalId(String localId) {
		EvaluationsRepository repository = new EvaluationsRepository();
		return repository.getByLocalId(localId);
	}
	
}
