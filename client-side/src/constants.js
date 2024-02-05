export const MAIN_ENDPOINT = 'http://localhost:8082';

export const USER_CONTROLLER = '/user';

export const LOGIN_ROUTE = '/login';
export const REGISTER_ROUTE = '/register';

export const LOGIN_ENDPOINT = MAIN_ENDPOINT + USER_CONTROLLER + LOGIN_ROUTE;
export const REGISTER_ENDPOINT = MAIN_ENDPOINT + USER_CONTROLLER + REGISTER_ROUTE;

export const COMMENTS_CONTROLLER = '/comments'
export const REVIEWS_CONTROLLER = '/reviews'

export const ADD_ROUTE = '/add';

export const COMMENTS_ENDPOINT = MAIN_ENDPOINT + COMMENTS_CONTROLLER + ADD_ROUTE;
export const REVIEWS_ENDPOINT = MAIN_ENDPOINT + REVIEWS_CONTROLLER + ADD_ROUTE;

export const BOTANICAL_GARDEN_CONTROLLER = '/botanical-gardens';

export const MAIN_ROUTE = '/';

export const BOTANICAL_GARDEN_ENDPOINT = MAIN_ENDPOINT + BOTANICAL_GARDEN_CONTROLLER + MAIN_ROUTE;

export const TOUR_CONTROLLER = '/tours';

export const PARTICIPATE_ROUTE = '/participate';

export const TOURS_ENDPOINT = MAIN_ENDPOINT + TOUR_CONTROLLER + PARTICIPATE_ROUTE;
