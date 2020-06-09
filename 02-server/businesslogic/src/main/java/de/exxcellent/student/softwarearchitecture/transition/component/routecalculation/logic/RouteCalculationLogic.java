package de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.logic;

import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.connector.RouteCalculationConnector;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.connector.types.Mode;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.connector.types.TripResponseTO;
import de.exxcellent.student.softwarearchitecture.transition.component.routecalculation.connector.types.TripTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteCalculationLogic {

   private final RouteCalculationConnector routeCalculationConnector;

  @Autowired
  public RouteCalculationLogic(RouteCalculationConnector routeCalculationConnector) {
    this.routeCalculationConnector = routeCalculationConnector;
  }

  public TripResponseTO calculateRoute(TripTO tripTO, Mode mode) {
    return routeCalculationConnector.calculateTrip(tripTO, mode);
  }
}