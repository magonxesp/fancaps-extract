import re


def replace_version(file_path, search, replacement):
    with open(file_path, 'r') as reader:
        content = reader.read()
    with open(file_path, 'w') as writer:
        writer.write(re.sub(search, replacement, content, flags=re.MULTILINE))
