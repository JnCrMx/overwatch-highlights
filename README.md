# Overwatch highlight reverse engineering

If you are just looking for the documentation,
have a look at [this](docs/index.rst) document or
at [Read the Docs](https://overwatch-highlights.readthedocs.io/en/latest/).

This repository is taken from a larger project of mine
(which I sadly can't publish yet),
so there might be some redundant parts
(like thumbnail extraction).

## TODO

- [x] Add Read The Docs page with (automatically) built Sphinx documentation
- [ ] Find values for all Maps
- [x] Find values for all Heros
- [x] Add enum for Skins
- [ ] Find values for all Skins (oof)

## Questions

- [ ] Why does the structure appear twice, but slightly different?
- [ ] What use have the other fields in the structure?
- [ ] Is the player name stored anywhere?
- [ ] Is it possible to distinguish own highlights from other's.
- [ ] Is there any structure behind the Hero IDs?
- [ ] Is there any structure behind the Skin IDs?

I'll note down my thoughts about these questions in a ``wip/``-directory soon.
