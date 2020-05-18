import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, concatMap, map} from 'rxjs/operators';
import {of} from 'rxjs';

import {actions} from '../../data-access/waypoint/state/route';
import {AuthenticationRepositoryService} from '../../../user/data-access/authentication/authentication-repository.service';
import {today} from '../../../shared/functions';
import {WaypointConnectorService} from '../../data-access/waypoint/connector/waypoint-connector.service';

@Injectable()
export class RouteEffects {

  constructor(private actions$: Actions,
              private connector: WaypointConnectorService,
              private session: AuthenticationRepositoryService) {}

  loadMyRoutes$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(actions.loadMyRouteOfToday),
      concatMap(() =>
            this.connector.findRoute(today(), 1).pipe(
              map(data => {
                return actions.loadMyRouteSuccess({data: data})
              }),
              catchError(error => of(actions.loadMyRouteFailure({error})))
          )
        )
      );
  });
}