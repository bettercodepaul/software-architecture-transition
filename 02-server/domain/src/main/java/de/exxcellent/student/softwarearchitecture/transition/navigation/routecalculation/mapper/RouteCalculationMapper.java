package de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.mapper;

import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types.CalculationMode;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types.LocationRequestDO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types.LocationResponseDO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.api.types.RouteCalculationDO;
import de.exxcellent.student.softwarearchitecture.transition.navigation.routecalculation.dataaccess.types.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class RouteCalculationMapper {

  private RouteCalculationMapper() {}

  public static final Function<List<LocationRequestDO>, TripDTO> toTripTO = input -> {
    var tripTO = new TripDTO();

    var locations = input.stream()
        .map(RouteCalculationMapper.toLocationTO)
        .collect(Collectors.toList());

    tripTO.setDeparture_time("2020-06-04T12:49:22.257Z");
    tripTO.setLocations(locations);

    return tripTO;
  };

  public static final Function<TripResponseDTO, RouteCalculationDO> toRouteCalculationDO = input -> {
    var routeCalculationDO = new RouteCalculationDO();

    var travelDuration = Math.round(input.getTravel_time() * 60); // minutes -> seconds
    var locations = input.getLocations().stream()
        .map(RouteCalculationMapper.toLocationResponseDO)
        .collect(Collectors.toList());

    routeCalculationDO.setSortedPositions(locations);
    routeCalculationDO.setTravelDurationInSeconds((long) travelDuration);

    return routeCalculationDO;
  };

  public static final Function<LocationRequestDO, LocationDTO> toLocationTO = input -> {
    var locationTO = new LocationDTO();

    locationTO.setId((int) input.getId());
    locationTO.set_index(input.getIndex());
    locationTO.setLat(input.getLatitude());
    locationTO.setLng(input.getLongitude());

    return locationTO;
  };

  public static final Function<LocationResponseDTO, LocationResponseDO> toLocationResponseDO = input -> {
    var locationDO = new LocationResponseDO();

    var travelDuration = Math.round(input.getTravel_time() * 60); // minutes -> seconds

    locationDO.setId(input.getId());
    locationDO.setIndex(input.get_index());
    locationDO.setLatitude(input.getLat());
    locationDO.setLongitude(input.getLng());
    locationDO.setTravelDuration((long) travelDuration);

    return locationDO;
  };


  public static final Function<CalculationMode, ModeDTO> toMode = mode -> {
    switch (mode) {
      case NORMAL: return ModeDTO.NONE;
      case OPTIMAL: return ModeDTO.BRUTE_FORCE;
      case RANDOM:
      default:
        return ModeDTO.RANDOM;
    }
  };
}