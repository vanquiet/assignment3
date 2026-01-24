DROP TABLE IF EXISTS missions;
DROP TABLE IF EXISTS ships;
DROP TABLE IF EXISTS pilots;

CREATE TABLE pilots (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        license_code VARCHAR(50) NOT NULL UNIQUE,
                        active BOOLEAN NOT NULL
);

CREATE TABLE ships (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       fuel_level DOUBLE PRECISION NOT NULL CHECK (fuel_level >= 0),
                       status VARCHAR(30) NOT NULL
);

CREATE TABLE missions (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(120) NOT NULL,
                          distance_km DOUBLE PRECISION NOT NULL CHECK (distance_km > 0),
                          required_fuel DOUBLE PRECISION NOT NULL CHECK (required_fuel >= 0),
                          ship_id INT NOT NULL,
                          pilot_id INT NOT NULL,
                          CONSTRAINT fk_ship FOREIGN KEY (ship_id) REFERENCES ships(id) ON DELETE CASCADE,
                          CONSTRAINT fk_pilot FOREIGN KEY (pilot_id) REFERENCES pilots(id) ON DELETE CASCADE
);
