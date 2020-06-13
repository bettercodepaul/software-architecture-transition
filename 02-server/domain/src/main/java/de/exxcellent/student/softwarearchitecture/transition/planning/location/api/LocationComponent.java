package de.exxcellent.student.softwarearchitecture.transition.planning.location.api;

import de.exxcellent.student.softwarearchitecture.transition.common.dataaccess.User;
import de.exxcellent.student.softwarearchitecture.transition.components.common.BusinessComponent;
import de.exxcellent.student.softwarearchitecture.transition.planning.location.api.types.LocationDO;

import java.util.List;

/**
 * TODO [AL]: Add class documentation
 *
 * @author Andre Lehnert, eXXcellent solutions consulting and software gmbh
 */
public interface LocationComponent extends BusinessComponent {

  List<LocationDO> findAll();

  LocationDO findById(Long locationId);

  LocationDO create(LocationDO newLocationDO, User user);

  LocationDO update(LocationDO locationDO, User user);

  void delete(Long id);
}