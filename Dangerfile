# Ignore inline messages which lay outside a diff's range of PR
github.dismiss_out_of_range_messages

# Common
has_label_wip = github.pr_title.match(/wip/i) || github.pr_labels.include?('wip') || github.pr_labels.include?('Wip') || github.pr_labels.include?('WIP')

#Android Lint
android_lint.skip_gradle_task = true
android_lint.filtering = true
Dir["library/build/reports/lint-results.xml"].each do |file|
  android_lint.report_file = file
  android_lint.lint(inline_mode: true)
end

# ktlint
ktlint.lint(inline_mode: true)

## WIP
fail('Is WIP !!') if has_label_wip

## Attention to a PR without any changes
if git.modified_files.empty? && git.added_files.empty? && git.deleted_files.empty?
  message('no fixed?')
end

# LGTM Image

lgtm.check_lgtm image_url: 'https://user-images.githubusercontent.com/40590821/137703484-e438c706-6598-434e-bd60-d656cc203043.png'