namespace java io.github.thrift
struct Person {
    1: required string userName,
    2: optional i64    favoriteNumber,
    3: optional list<string> interests
}

