package com.fusionflux.portalcubed.accessor;

import com.fusionflux.portalcubed.entity.ExperimentalPortal;

import java.util.List;

public interface EntityPortalsAccess {

	List<ExperimentalPortal> portalcubed$getPortalList();

	void portalcubed$addPortalToList(ExperimentalPortal portal);

}
