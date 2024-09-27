import argparse
import re
from functions import replace_version

parser = argparse.ArgumentParser(prog='Gradle version replace',)
parser.add_argument('version')
args = parser.parse_args()

version_code = re.sub(r'^v', '', args.version)

replace_version(
    file_path='build.gradle.kts',
    search=r'^version = "v?[0-9.]+\-?[a-z]*\.?[0-9]*"',
    replacement=f'version = "{version_code}"'
)
