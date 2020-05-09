package de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.businesslogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.common.validation.Preconditions;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.RouteCalculationComponent;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.api.types.RouteCalculationDO;
import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.businesslogic.logic.RouteCalculationLogic;

import de.exxcellent.student.softwarearchitecture.transition.businesslogic.components.routecalculation.impl.businesslogic.mapper.RouteCalculationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

 @Component
public class RouteCalculationFacade implements RouteCalculationComponent {

  private final RouteCalculationLogic routeCalculationLogic;

  @Autowired
  public RouteCalculationFacade(RouteCalculationLogic routeCalculationLogic) {
    this.routeCalculationLogic = routeCalculationLogic;
  }


   @Override
   public RouteCalculationDO calculateRoute(List<LocationRequestDO> locationSequence) {
     Preconditions.checkNotNull(locationSequence, "Location sequence must not be null");
     Preconditions.checkArgument(locationSequence.size() > 1,
         "Location sequence must contain more than 1 location");

     var tripTO = RouteCalculationMapper.toTripTO.apply(locationSequence);

     var tripResponseTO = routeCalculationLogic.calculateRoute(tripTO);

     return RouteCalculationMapper.toRouteCalculationDO.apply(tripResponseTO);
   }
 }
