from django.contrib import admin
from polls.models import Question, Choice


class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 2
class QuestionAdmin(admin.ModelAdmin):
    fieldsets = [
        (None, {'fields':['question_text']}),
        ('Date Information',{'fields':['pub_date'], 'classes':['collapse']}),    
    ] #필드 순서 변경
    inlines = [ChoiceInline]
    list_display = ('question_text','pub_date')
    list_filter = ['pub_date']
    search_fields = ['question_text']
    
# Register your models here.
admin.site.register(Question,QuestionAdmin)
admin.site.register(Choice)
