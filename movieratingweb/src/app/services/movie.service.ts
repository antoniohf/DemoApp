import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Movie } from '../models';

@Injectable({ providedIn: 'root' })
export class MovieService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<Movie[]>(`${environment.apiUrl}/movies/`);
    }

    addMovie(movie: Movie) {
      this.http.post<Movie>(`${environment.apiUrl}/movies/`, movie);
    }
}
