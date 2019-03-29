package org.rkcpinfo.producer.incoming;

import java.util.ArrayList;
import java.util.List;

import org.rkcpinfo.producer.model.Engineering;
import org.rkcpinfo.producer.model.Engineering.Genre;
import org.springframework.stereotype.Component;

@Component
public class EngineeringPublisher {
	private long nextEngineeringId;

	public EngineeringPublisher() {
		this.nextEngineeringId = 1001l;
	}

	public List<Engineering> getEngineerings() {
		List<Engineering> Engineerings = new ArrayList<Engineering>();

		Engineerings.add(createItEngineering());
		Engineerings.add(createItEngineering());
		Engineerings.add(createItEngineering());
		Engineerings.add(createItEngineering());
		Engineerings.add(createItEngineering());
		Engineerings.add(createCivilEngineering());
		Engineerings.add(createCivilEngineering());
		Engineerings.add(createCivilEngineering());
		Engineerings.add(createCivilEngineering());
		Engineerings.add(createCivilEngineering());
		Engineerings.add(createElectronicsEngineering());
		Engineerings.add(createElectronicsEngineering());
		Engineerings.add(createElectronicsEngineering());
		Engineerings.add(createElectronicsEngineering());
		Engineerings.add(createElectronicsEngineering());
		Engineerings.add(createAutomobileEngineering());
		Engineerings.add(createAutomobileEngineering());
		Engineerings.add(createAutomobileEngineering());
		Engineerings.add(createAutomobileEngineering());
		Engineerings.add(createAutomobileEngineering());

		return Engineerings;
	}

	Engineering createItEngineering() {
		return createEngineering("", Genre.IT);
	}

	Engineering createCivilEngineering() {
		return createEngineering("", Genre.civil);
	}

	Engineering createElectronicsEngineering() {
		return createEngineering("", Genre.electronics);
	}

	Engineering createAutomobileEngineering() {
		return createEngineering("", Genre.Automobile);
	}

	Engineering createEngineering(String title, Genre genre) {
		Engineering engineering = new Engineering();
		engineering.setEngineerId(nextEngineeringId++);
		if (title == "") {
			title = "# " + Long.toString(engineering.getEngineerId());
		}
		engineering.setEngineertitle(title);
		engineering.setGenre(genre);

		return engineering;
	}
}










