#!/bin/bash

stock_slug_file="/storage/emulated/0/#mods/slug.txt"
file_path="/storage/emulated/0/#mods/stockdatapredictorvalue.txt"
failed_slugs_file="/storage/emulated/0/#mods/slugfailed.txt"

while IFS= read -r stock_slug || [[ -n "$stock_slug" ]]; do
    url="https://www.tickertape.in${stock_slug}"
    response=$(curl -s "$url")

    if [ $? -eq 0 ]; then
        # Extract and write matched parts to file and print to screen
        extract_and_write_matches "$response" "$file_path"
    else
        echo "Request failed: $url"
        echo "$stock_slug" >> "$failed_slugs_file"
    fi
done < "$stock_slug_file"

echo "Data extraction completed."

extract_and_write_matches() {
    content="$1"
    file_path="$2"
    pattern1="<title>.*</title>"
    pattern2=">[0-9]{2,3}<span"

    matches=$(echo "$content" | grep -Eo "$pattern1|$pattern2")

    while IFS= read -r match || [[ -n "$match" ]]; do
        echo "$match" >> "$file_path"
        echo "$match"
    done <<< "$matches"
}
