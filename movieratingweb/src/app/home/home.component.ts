import { Component } from '@angular/core';
import { first } from 'rxjs/operators';

import { Movie } from '../models';
import { MovieService, AuthenticationService } from '../services';

@Component({ templateUrl: 'home.component.html' })
export class HomeComponent {
    loading = false;
    movies: Movie[];

    constructor(private movieService: MovieService) { }

    ngOnInit() {
        this.loading = true;
        this.movieService.getAll().pipe(first()).subscribe(movies => {
            this.loading = false;
            this.movies = movies;
        });
    }
}
