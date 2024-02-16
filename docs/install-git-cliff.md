# Installing git cliff

The Gir cliff program allow to automate the CHANGELOG.md creation by the commits of the repository.

## Install rust

For Linux distros.
```sh
$ curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

For windows.

[See available installation methods](https://forge.rust-lang.org/infra/other-installation-methods.html)

## Install git cliff with cargo
```sh
$ cargo install git-cliff
```

## Check git cliff is installed
NOTE: is normal if appear an error telling the `cliff.toml` file is nor found.

```sh
$ git cliff
```
