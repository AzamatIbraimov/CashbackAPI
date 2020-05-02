package kg.nurtelecom.cashbackapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationFullModel extends OrganizationModel {

    public OrganizationFullModel(OrganizationModel organizationModel, List<BonusShortModel> bonusShortModels, List<EventFullModel> eventModels, List<FilialShortModel> filialShortModels) {
        super(organizationModel.getId(), organizationModel.getImage(), organizationModel.getStatus(), organizationModel.getName(), organizationModel.getCategoryId(), organizationModel.getCategoryName(), organizationModel.getDescription());
        this.bonusShortModels = bonusShortModels;
        this.eventModels = eventModels;
        this.filialShortModels = filialShortModels;
    }

    List<BonusShortModel> bonusShortModels;
    List<EventFullModel> eventModels;
    List<FilialShortModel> filialShortModels;
}
