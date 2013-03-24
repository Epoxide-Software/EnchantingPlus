import sys
import os
import commands
import fnmatch
import re
import subprocess, shlex
import argparse
import shutil

def cmdsplit(args):
    if os.sep == '\\':
        args = args.replace('\\', '\\\\')
    return shlex.split(args)

def main():
    langs = []

    for files in os.listdir("./resources/eplus/lang"):
        if files.endswith(".xml"):
            langs.append(files)

    file = open("./resources/eplus/lang/languages.txt", 'wb')
    for lang in langs:
        file.write('%s\n' % lang)
    file.close()

if __name__ == '__main__':
        main()

