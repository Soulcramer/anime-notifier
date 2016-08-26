package moe.notify.animenotifier.network.model;

import moe.notify.animenotifier.domain.model.DomainModel;

/**
 * Created by GOKU on 27/08/2016.
 */

public interface NetworkModel {

    void convertToDomainModel(DomainModel domainModel);

    void convertToNetworkModel(DomainModel domainModel);


}
